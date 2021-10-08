package se.hedvig.task.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Report {

    private List<Column> columns = new ArrayList<>();
}
