package ${entity.servicePackageName};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${entity.daoPackageName}.${entity.className}Mapper;
import ${entity.entityPackageName}.${entity.className};
import ${entity.servicePackageName}.${entity.className}Service;

/**
 * 
 * @ClassName ${entity.className}Service
 * @Description
 * @author ${entity.author}
 * @date ${entity.createTime}
 */
@Service
public class ${entity.className}Service {

    @Autowired
    private ${entity.className}Mapper ${entity.classInstanceName}Mapper;
    
    public void insert(${entity.className} ${entity.classInstanceName}) {
${entity.classInstanceName}Mapper.insert(${entity.classInstanceName});
    }
    
    public void update(${entity.className} ${entity.classInstanceName}) {
${entity.classInstanceName}Mapper.update(${entity.classInstanceName});
    }

    public ${entity.className} getById(${entity.idSimpleType} ${entity.idName}) {
        return ${entity.classInstanceName}Mapper.getById(${entity.idName});
    }
}