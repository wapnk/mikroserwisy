package app.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModelQueryParams {

    public String columnName;
    public String operation;
    public Object value;

    public int pageNumber;
    public Integer pageSize;
    public String[] orderBy;
}
