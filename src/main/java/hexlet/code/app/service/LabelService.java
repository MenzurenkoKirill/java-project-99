package hexlet.code.app.service;

import hexlet.code.app.dto.labels.LabelCreateDTO;
import hexlet.code.app.dto.labels.LabelUpdateDTO;
import hexlet.code.app.dto.labels.LabelDTO;
import hexlet.code.app.mapper.LabelMapper;
import hexlet.code.app.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private LabelMapper labelMapper;

    public List<LabelDTO> getAll() {
        return labelMapper.map(labelRepository.findAll());
    }

    public LabelDTO findById(Long id) {
        var label = labelRepository.findById(id).orElseThrow();
        return labelMapper.map(label);
    }

    public LabelDTO create(LabelCreateDTO labelData) {
        var label = labelMapper.map(labelData);
        labelRepository.save(label);
        return labelMapper.map(label);
    }

    public LabelDTO update(LabelUpdateDTO labelData, Long id) {
        var label = labelRepository.findById(id).orElseThrow();
        labelMapper.map(labelData, label);
        labelRepository.save(label);
        return labelMapper.map(label);
    }

    public void delete(Long id) {
        labelRepository.deleteById(id);
    }
}
