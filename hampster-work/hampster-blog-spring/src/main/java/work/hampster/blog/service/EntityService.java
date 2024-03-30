package work.hampster.blog.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import work.hampster.blog.service.impl.EntityServiceImpl;

import java.util.Map;

/**
 *
 * @param <M> mapper
 * @param <T> entity / model
 */
public interface EntityService<M extends BaseMapper<T>, T> {

    Map<String, Object> cQuery(HttpServletRequest rq, int page, int pageSize);
    Map<String, Object> cQueryById(int pk);
    String cUpdateById(HttpServletRequest rq, int pk);
    String cDeleteById(int pk);
    String cInsert(HttpServletRequest rq);

}
