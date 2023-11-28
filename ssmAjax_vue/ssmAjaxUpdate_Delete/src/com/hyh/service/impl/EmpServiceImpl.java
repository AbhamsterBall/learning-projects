package com.hyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyh.mapper.EmpMapper;
import com.hyh.model.Emp;
import com.hyh.service.EmpService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

    EntityServiceImpl service = new EntityServiceImpl(Emp.class, this);

    @Override
    public Map<String, Object> cQuery(HttpServletRequest rq, int page) {
        return service.cQuery(rq, page);
    }

    @Override
    public Map<String, Object> cQueryById(int pk) {
        return service.cQueryById(pk);
    }

    @Override
    public String cUpdateById(HttpServletRequest rq, int pk) {
        return service.cUpdateById(rq, pk);
    }

    @Override
    public String cDeleteById(int pk) {
        return service.cDeleteById(pk);
    }
//    public EmpServiceImpl() {
//        super(Emp.class);
//    }
}
