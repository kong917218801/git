package ${entity.managerPackageName};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ${entity.servicePackageName}.${entity.className}Service;

/**
 * 
 * @ClassName: ${entity.className}Manager
 * @Description:
 * @author ${entity.author}
 * @date: ${entity.createTime}
 */
@Component
public class ${entity.className}Manager{
	
	@Autowired
    private ${entity.className}Service ${entity.classInstanceName}Service;
	
}