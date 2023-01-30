package com.runners.service;

import com.runners.domain.Doctor;
import com.runners.dto.DoctorDTO;
import com.runners.exception.ConflictException;
import com.runners.exception.ResourceNotFoundException;
import com.runners.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    public void createDoctor(Doctor doctor) {
        if(doctorRepository.existsByTcNo(doctor.getTcNo())){
            throw new ConflictException("TcNo already exists");
        }
        doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDocs() {
        return doctorRepository.findAll();
    }

    public DoctorDTO getDocById(Long id) {
//        if (!doctorRepository.existsById(id)){
//            throw new ResourceNotFoundException("Doctor not found by id");
//        }
//        Doctor doctor = doctorRepository.getById(id);
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor not found by id"));
        DoctorDTO doctorDTO = new DoctorDTO(doctor);
        return doctorDTO;
    }

    public void deleteById(Long id) {
       if (doctorRepository.existsById(id)){
           doctorRepository.deleteById(id);
       }else throw new ResourceNotFoundException("Doctor Not Found by id " + id);

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
}
