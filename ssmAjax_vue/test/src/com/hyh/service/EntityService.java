package com.hyh.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface EntityService<T> {
    Map<String, Object> cQuery(HttpServletRequest rq, int page);
    Map<String, Object> cQueryById(int pk);
    String cUpdateById(HttpServletRequest rq, int pk);
    String cDeleteById(int pk);
    String cInsert(HttpServletRequest rq);
}
