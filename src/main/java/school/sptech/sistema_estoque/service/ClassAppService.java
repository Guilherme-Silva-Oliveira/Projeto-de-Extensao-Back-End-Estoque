
package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.classapp.LabelsRequest;
import school.sptech.sistema_estoque.dto.classapp.TagsRequest;
import school.sptech.sistema_estoque.port.ClassAppPort;

@Service
public class ClassAppService {
    private final ClassAppPort classAppPort;

    public ClassAppService(ClassAppPort classAppPort) {
        this.classAppPort = classAppPort;
    }

    public TagsRequest getTags() {
        return classAppPort.getTags();
    }

    public String getGroups() {
        return classAppPort.getGroups();
    }

    public String getStudents() {
        return classAppPort.getStudents();
    }

    public String getStaffs() {
        return classAppPort.getStaffs();
    }

    public LabelsRequest getLabels() {
        return classAppPort.getLabels();
    }
}

