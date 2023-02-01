package com.runners.service;


import com.runners.domain.Doctor;
import com.runners.dto.AppDto;
import com.runners.dto.DocResponse;
import com.runners.dto.DoctorDTO;
import com.runners.exception.ConflictException;
import com.runners.exception.ResourceNotFoundException;
import com.runners.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    @Lazy
    private DoctorRepository doctorRepository;

    @Autowired
    @Lazy
    private AppointmentService appointmentService;

    public void createDoctor(Doctor doctor) {

        if (doctorRepository.existsByTcNo(doctor.getTcNo())) {
            throw new ConflictException("Tcno already exists !");
        }
        doctorRepository.save(doctor);

    }

    public List<Doctor> getAll() {

        return doctorRepository.findAll();
    }


    public List<DocResponse> getAllDoc() {
        List<Doctor> doctorList = doctorRepository.findAll();
        List<DocResponse> docResponseList = new ArrayList<>();



        for(Doctor w: doctorList){
            DocResponse docResponse = new DocResponse(w);
            List<AppDto> appDtoList = appointmentService.findAppDtoByDoctor(w);
            docResponse.setAppointmentList(appDtoList);
            docResponseList.add(docResponse);
        }
        return docResponseList;
    }



    public DoctorDTO getByIdDTO(Long id) {

        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor not found by id : " + id));
        DoctorDTO dto = new DoctorDTO(doctor);

        return dto;

    }

    public void deleteDoc(Long id) {

        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Doctor not found by id : " + id);

    }

    public void updateDoctor(Long id, DoctorDTO doctorDTO) {

       boolean existTc = doctorRepository.existsByTcNo(doctorDTO.getTcNo());

        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor not found by id : " + id));

       if(existTc && ! doctorDTO.getTcNo().equals(doctor.getTcNo())){
           throw new ConflictException("Tcno already exists !");
       }
       doctor.setName(doctorDTO.getFirstName());
       doctor.setTcNo(doctorDTO.getTcNo());
       doctor.setDepartmentName(doctorDTO.getDepartment());
       doctor.setPrefixName(doctorDTO.getPrefix());
       doctor.setDateOfGraduate(doctorDTO.getDateOfGraduate());
       doctor.setDateOfStart(doctorDTO.getDateOfStart());

       doctorRepository.save(doctor);

    }

    public Doctor getDoctorById(Long id){

        return doctorRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Doctor not found by id :" + id));
    }

    public boolean existById(Long id){
        boolean exists = doctorRepository.existsById(id);

        return exists;
    }


}
