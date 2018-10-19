package ${entity.daoPackageName};

import ${entity.entityPackageName}.${entity.className};
import ${entity.idType};

/**
 *
 * @ClassName ${entity.className}Mapper
 * @Description
 * @author ${entity.author}
 * @date ${entity.createTime}
 */
public interface ${entity.className}Mapper {

${entity.idSimpleType} insert(${entity.className} ${entity.classInstanceName});

    Integer update(${entity.className} ${entity.classInstanceName});

${entity.className} getById(${entity.idSimpleType} ${entity.idName});
}