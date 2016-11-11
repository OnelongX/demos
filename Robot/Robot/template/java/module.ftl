<#escape x as (x)!"">

package com.sxit.model.entmgt;

import java.util.Date;

public class ${table.bean_name} {
    <#if table.field_list??>
        <#list table.field_list as field>
    private ${field.object_java_type} ${field.field_name};
        </#list>
    </#if>

    <#if table.field_list??>
        <#list table.field_list as field>
    /*
     * 设置${field.commentLong}
     * @params ${field.object_java_type} ${field.field_name}
     */
    public void set${field.method_name}(${field.object_java_type} ${field.field_name})
    {
        this.${field.field_name} = ${field.field_name};
    }
    /*
     * 获取${field.commentLong}
     *
     * @return ${field.object_java_type}
     */
    public ${field.object_java_type} get${field.method_name}()
    {
        return this.${field.field_name};
    }

        </#list>
    </#if>

}

</#escape>