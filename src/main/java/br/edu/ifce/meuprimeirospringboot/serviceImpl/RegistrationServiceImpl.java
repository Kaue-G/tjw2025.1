package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.beans.Registration;
import br.edu.ifce.meuprimeirospringboot.beans.User;
import br.edu.ifce.meuprimeirospringboot.beans.ClassEntity;
import br.edu.ifce.meuprimeirospringboot.dto.RegistrationDTO;
import br.edu.ifce.meuprimeirospringboot.repository.ClassRepository;
import br.edu.ifce.meuprimeirospringboot.repository.RegistrationRepository;
import br.edu.ifce.meuprimeirospringboot.repository.UserRepository;
import br.edu.ifce.meuprimeirospringboot.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private UserRepository userRepository;

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	@Override
	public Optional<RegistrationDTO> findById(Long id) {
		return registrationRepository.findById(id).map(this::toDTO);
	}

	@Override
	public List<RegistrationDTO> findAll() {
		return registrationRepository.findAll().stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<RegistrationDTO> findByClass(Long classId) {
		return registrationRepository.findByClassEntityId(classId).stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<RegistrationDTO> findByStudent(Long studentId) {
		return registrationRepository.findByStudentId(studentId).stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public void save(RegistrationDTO registrationDTO) {
		Registration registration;
		
		if (registrationDTO.getId() != null) {
			registration = registrationRepository.findById(registrationDTO.getId())
					.orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada."));
		} else {
			registration = new Registration();
		}
		
		ClassEntity classEntity = classRepository.findById(registrationDTO.getClassId()).orElseThrow(() ->
			new IllegalArgumentException("Turma não encontrada"));
		
		User student = userRepository.findById(registrationDTO.getStudentId()).orElseThrow(() -> 
				new IllegalArgumentException("Aluno não encontrado"));
		
		registration.setClassEntity(classEntity);
		registration.setStudent(student);
		registration.setActive(registrationDTO.isActive());
		
		registrationRepository.save(registration);
	}

	@Override
	public void deactivateRegistration(Long id) {
		registrationRepository.findById(id).ifPresent(registration -> {
			registration.setActive(false);
			registrationRepository.save(registration);
		});;
	}
	
	private RegistrationDTO toDTO(Registration registration) {
		
		RegistrationDTO dto = new RegistrationDTO();
		
		dto.setId(registration.getId());
		dto.setStudentId(registration.getStudent().getId());
		dto.setStudentName(registration.getStudent().getName());
		dto.setClassId(registration.getClassEntity().getId());
		dto.setRegistrationSubject(registration.getClassEntity().getSubject().getName());
		dto.setRegistrationTeacher(registration.getClassEntity().getTeacher().getName());
		dto.setActive(registration.isActive());

		if (registration.getRegistrationDate() != null) {			
			dto.setRegistrationDateFormatted(registration.getRegistrationDate().format(formatter));
		}
		return dto;
	}
	
}
