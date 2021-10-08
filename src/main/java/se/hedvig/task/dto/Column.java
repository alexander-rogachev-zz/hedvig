package se.hedvig.task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Column {

    private String month;
    private Integer numberOfContacts = 0;
    private Integer AGWP;
    private Integer EGWP;

}
