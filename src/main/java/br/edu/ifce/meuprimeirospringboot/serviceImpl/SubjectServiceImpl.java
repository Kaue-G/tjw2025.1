package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.beans.Subject;
import br.edu.ifce.meuprimeirospringboot.dto.SubjectDTO;
import br.edu.ifce.meuprimeirospringboot.repository.SubjectRepository;
import br.edu.ifce.meuprimeirospringboot.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Override
	public SubjectDTO findById(Long id) {
		return subjectRepository.findById(id).map(this::toDTO).orElse(null);
	}

	@Override
	public List<SubjectDTO> findAll() {
		return subjectRepository.findAll().stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public SubjectDTO edit(Long id, SubjectDTO updatedSubject) {
		Subject subject = subjectRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Disciplina não encontrada!"));
		
		subject.setName(updatedSubject.getName());
		subject.setPeriod(updatedSubject.getPeriod());
		subject.setYear(updatedSubject.getYear());
		
		subjectRepository.save(subject);
		
		return toDTO(subject);
	}

	@Override
	public void save(SubjectDTO subjectDTO) {
		Subject subject = subjectDTO.getId() != null 
				? subjectRepository.findById(subjectDTO.getId()).orElse(new Subject()) 
				: new Subject();
		
		subject.setName(subjectDTO.getName());
		subject.setYear(subjectDTO.getYear());
		subject.setPeriod(subjectDTO.getPeriod());
		
		subjectRepository.save(subject);
	}

	@Override
	public void deleteById(Long id) {
		subjectRepository.deleteById(id);
		
	}
	
	private SubjectDTO toDTO(Subject subject) {
		
		SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        dto.setYear(subject.getYear());
        dto.setPeriod(subject.getPeriod());
        dto.setQtStudents(subject.getStudents() != null ? subject.getStudents().size() : 0);
        
        return dto;
    }

}
