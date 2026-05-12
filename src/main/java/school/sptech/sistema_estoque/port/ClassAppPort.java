
package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.dto.classapp.LabelsRequest;
import school.sptech.sistema_estoque.dto.classapp.TagsRequest;

public interface ClassAppPort {
    TagsRequest getTags();
    String getGroups();
    String getStudents();
    String getStaffs();
    LabelsRequest getLabels();
}
