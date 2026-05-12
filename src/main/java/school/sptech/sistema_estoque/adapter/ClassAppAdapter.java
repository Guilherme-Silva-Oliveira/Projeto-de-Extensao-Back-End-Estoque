
package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.client.ClassAppClient;
import school.sptech.sistema_estoque.dto.classapp.LabelsRequest;
import school.sptech.sistema_estoque.dto.classapp.TagsRequest;
import school.sptech.sistema_estoque.port.ClassAppPort;

@Component
public class ClassAppAdapter implements ClassAppPort {
    private final ClassAppClient client;

    public ClassAppAdapter(ClassAppClient client) {
        this.client = client;
    }

    @Override
    public TagsRequest getTags() {
        return client.getTags();
    }

    @Override
    public String getGroups() {
        return client.getGroups();
    }

    @Override
    public String getStudents() {
        return client.getStudents();
    }

    @Override
    public String getStaffs() {
        return client.getStaffs();
    }

    @Override
    public LabelsRequest getLabels() {
        return client.getLabels();
    }
}

