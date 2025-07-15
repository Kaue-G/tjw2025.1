package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.beans.ClassEntity;
import br.edu.ifce.meuprimeirospringboot.beans.Subject;
import br.edu.ifce.meuprimeirospringboot.beans.User;
import br.edu.ifce.meuprimeirospringboot.dto.ClassDTO;
import br.edu.ifce.meuprimeirospringboot.repository.ClassRepository;
import br.edu.ifce.meuprimeirospringboot.repository.SubjectRepository;
import br.edu.ifce.meuprimeirospringboot.repository.UserRepository;
import br.edu.ifce.meuprimeirospringboot.service.ClassService;

@Service
public class ClassServiceImpl implements ClassService{

	@Autowired
	private ClassRepository classRepository;
	@Autowired 
	private SubjectRepository subjectRepository;
    @Autowired 
    private UserRepository userRepository;
	
	@Override
	public List<ClassDTO> findAll() {
		return classRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Override
	public ClassDTO findById(Long id) {
		return classRepository.findById(id).map(this::toDTO).orElse(null);
	}

	@Override
	public void save(ClassDTO classDTO) {
		ClassEntity classData = classDTO.getId() != null
				? classRepository.findById(classDTO.getId()).orElse(new ClassEntity())
				: new ClassEntity();
		
		Subject subject = subjectRepository.findById(classDTO.getSubjectId()).orElse(null);
		User teacher = userRepository.findById(classDTO.getTeacherId()).orElse(null);
		
		
		classData.setRoom(classDTO.getRoom());
		classData.setSemester(classDTO.getSemester());
		classData.setSubject(subject);
		classData.setTeacher(teacher);
		
		classRepository.save(classData);
	}

	@Override
	public void deleteById(Long id) {
		classRepository.deleteById(id);
	}

	private ClassDTO toDTO(ClassEntity classData) {
		ClassDTO dto = new ClassDTO();
        
		dto.setId(classData.getId());
        dto.setSubjectId(classData.getSubject().getId());
        dto.setTeacherId(classData.getTeacher().getId());
        dto.setSemester(classData.getSemester());
        dto.setRoom(classData.getRoom());
        dto.setSubjectName(classData.getSubject().getName());
        dto.setTeacherName(classData.getTeacher().getName());
        
        return dto;
    }
}
