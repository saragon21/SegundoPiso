/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.services;

import com.segundo.piso.beans.Asistencia;
import com.segundo.piso.beans.Clase;
import com.segundo.piso.beans.Maestro;
import com.segundo.piso.beans.Movimiento;
import com.segundo.piso.beans.ReporteAsistencias;
import com.segundo.piso.beans.Response;
import com.segundo.piso.daos.DAOAttendence;
import com.segundo.piso.daos.DAOClass;
import com.segundo.piso.daos.DAOMovement;
import com.segundo.piso.daos.DAOParameter;
import com.segundo.piso.daos.DAOTeacher;
import com.segundo.piso.util.CTEApp;
import com.segundo.piso.util.CTEMessages;
import com.segundo.piso.util.DateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SARAGON
 */
@Component
public class AttendenceService {

    @Autowired
    DAOAttendence daoAttendence;

    @Autowired
    private DAOMovement daoMovement;

    @Autowired
    private DAOClass daoClase;

    @Autowired
    private DAOTeacher daoTeacher;

    @Autowired
    private DAOParameter daoParameter;

    public List<Asistencia> getAttendenceByStudent(int idAlumno) {
        return daoAttendence.getAttendenceByIdStudent(idAlumno);
    }

    public Response saveAttendence(Asistencia asistencia) {
        List<Movimiento> movements = this.daoMovement.getAvailableMovements(asistencia.getIdAlumno().getId(), CTEApp.ACTIVE);
        Response response = new Response();
        int daysAvailable;
        boolean valid = false;
        String message = CTEMessages.DAYS_NOT_AVAILABLE;

        for (Movimiento movement : movements) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(movement.getFechaInicio());
            calendar.add(Calendar.DAY_OF_YEAR, 31);
            Date endDate = calendar.getTime();
            Date today = new Date();

            if (today.after(endDate)) {
                movement.setActivo(false);
                //TODO Usuario
                movement.setLastModUser("saragon");
                this.daoMovement.update(movement);
                message += " Paquete: " + DateUtil.formatDate(movement.getFechaInicio(), DateUtil.DD_MM_YYYY);
            } else {
                daysAvailable = daysAvailable(movement.getIdMovimiento(),
                        asistencia.getIdAlumno().getId(), movement.getIdEvento().getDiasMes());

                if (daysAvailable > 0) {
                    asistencia.setLastModUser("saragon");
                    asistencia.setDiasRestantes(--daysAvailable);
                    asistencia.setIdMovimiento(movement);
                    this.daoAttendence.save(asistencia);
                    valid = true;
                    message = "Restan " + asistencia.getDiasRestantes()
                            + " clases. Paquete: " + DateUtil.formatDate(movement.getFechaInicio(), DateUtil.DD_MM_YYYY);
                    break;
                } else {
                    movement.setActivo(false);
                    movement.setLastModUser("saragon");
                    this.daoMovement.update(movement);
                    message += " Paquete: " + DateUtil.formatDate(movement.getFechaInicio(), DateUtil.DD_MM_YYYY);
                }
            }
        }

        response.setValid(valid);
        response.setMessage(message);

        return response;
    }

    private int daysAvailable(int idMovement, int idStudent, int monthDays) {
        int daysUsed = (int) daoAttendence.getDaysUsed(idStudent, idMovement);

        if (daysUsed < monthDays) {
            daysUsed = (monthDays - daysUsed);
        } else {
            daysUsed = 0;
        }

        return daysUsed;
    }

    public Response deleteAttendence(int idAttendence) {
        Response response = new Response();
        Asistencia attendence = this.daoAttendence.getRecordById(idAttendence, Asistencia.class);
        Date today = DateUtil.format(new Date(), DateUtil.DD_MM_YYYY);
        Date attendenceDate = DateUtil.format(attendence.getFecha(), DateUtil.DD_MM_YYYY);

        if (today.getTime() == attendenceDate.getTime()) {
            Movimiento movement = attendence.getIdMovimiento();
            movement.setActivo(true);
            //TODO ususario
            movement.setLastModUser("saragon");
            this.daoAttendence.delete(attendence);
            this.daoMovement.update(movement);
            response.setMessage(CTEMessages.ATTENDENCE_DELETED);
            response.setValid(true);
        } else {
            response.setMessage(CTEMessages.DELETE_ATTENDENCE);
            response.setValid(false);
        }

        return response;
    }

    public ReporteAsistencias getAttendenceByClass(int idClase, int idMaestro, boolean pagadas) {
        List<Asistencia> asistencias = this.daoAttendence.getAttendenceByClass(idClase, idMaestro, pagadas);
        ReporteAsistencias reporte = new ReporteAsistencias();
        Clase clase = this.daoClase.getRecordById(idClase, Clase.class);
        Maestro maestro = this.daoTeacher.getRecordById(idMaestro, Maestro.class);
        int numStudents = 0;
        String date = "";
        String currentDate = null;
        float payment = 0;

        if (!asistencias.isEmpty()) {
            date = DateUtil.formatDate(asistencias.get(0).getFecha(), DateUtil.DD_MM_YYYY);
            for (Asistencia asistencia : asistencias) {
                currentDate = DateUtil.formatDate(asistencia.getFecha(), DateUtil.DD_MM_YYYY);
                if (date.equals(currentDate)) {
                    numStudents++;
                } else {
                    payment += calculatePayment(numStudents, idClase, maestro);
                    date = currentDate;
                    numStudents = 1;
                }
            }
        }

        payment += calculatePayment(numStudents, idClase, maestro);
        reporte.setPago(payment);
        reporte.setAttendence(asistencias);
        reporte.setMaestro(maestro);
        reporte.setClase(clase);

        return reporte;
    }

    private float calculatePayment(int numStudents, int idClase, Maestro maestro) {
        float valorClase = Float.valueOf(daoParameter.getParameterByParameter(CTEApp.VALOR_CLASE, true).getValor());
        String danzaAfricanaStr = daoParameter.getParameterByParameter(CTEApp.DANZA_AFRICANA, true).getValor();
        Clase danzaAfricana = this.daoClase.getAllRecords(Clase.class, Restrictions.eq("nombreClase", danzaAfricanaStr)).get(0);
        float payment = 0;
        float fixPayment = Float.valueOf(daoParameter.getParameterByParameter(CTEApp.HORA_FIJA, true).getValor());
        if (idClase != danzaAfricana.getId()) {
            if (!maestro.getPorcentaje()) {
                payment += maestro.getPago();
            } else {
                if (numStudents < 4) {
                    float total = valorClase * numStudents;
                    payment += total * (maestro.getPago() / 100);
                } else {
                    payment += fixPayment;
                }
            }
        } else {
            float total = valorClase * numStudents;
            payment += total * (maestro.getPago() / 100);
        }
        
        return payment;
    }
}
