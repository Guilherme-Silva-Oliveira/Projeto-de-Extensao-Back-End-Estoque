package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.client.ClassAppClient;
import school.sptech.sistema_estoque.dto.classapp.LabelsRequest;
import school.sptech.sistema_estoque.dto.classapp.TagsRequest;

@Service
public class ClassAppService {
    private final ClassAppClient client;
    public ClassAppService(ClassAppClient client) {
        this.client = client;
    }

    public TagsRequest getTags(){
        return client.getTags();
    }

    public String getStudents(){return client.getStudents();}

    public String getStaffs(){return client.getStaffs();}

    public LabelsRequest getLabels(){return client.getLabels();}
}
