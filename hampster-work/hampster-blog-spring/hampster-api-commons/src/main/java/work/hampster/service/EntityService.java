package work.hampster.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jakarta.servlet.http.HttpServletRequest;

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
