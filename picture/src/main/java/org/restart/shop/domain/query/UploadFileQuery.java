package org.restart.shop.domain.query;

import lombok.Getter;
import lombok.Setter;
import org.restart.shop.annotation.QueryField;
import org.restart.shop.core.doamin.BaseQuery;
@Setter
@Getter
public class UploadFileQuery extends BaseQuery {
    @QueryField(type = QueryField.CompareType.EQ)
    private String fileMd5;
}
