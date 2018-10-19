<div class="page-wrapper" id="${entity.classInstanceName}-page">
    <div id="page-content">
        <table id="${entity.classInstanceName}-datagrid"></table>
    </div>
</div>
			
			<script type="text/javascript">
                $(function () {
                    getWidgets();
                    renderWidgets();
                })

                function getWidgets() {
                    window.${entity.classInstanceName}Datagrid = $("#${entity.classInstanceName}-page #page-content #${entity.classInstanceName}-datagrid");
                }

                function renderWidgets() {
                    render${entity.className}Datagrid();
                }

                function render${entity.className}Datagrid() {
                    var dg = window.${entity.classInstanceName}Datagrid;
                    dg.datagrid({
                        url: '${entity.classInstanceName}_query.do',
                        pagination: true,
                        queryParams: {},
                        columns: [[
                            {field: 'checked', width: 30, checkbox: true},
                            {field: '${entity.idName}', title: '编号', width: 60, filter: true, sortable: true},
							<#list entity.propList as prop>
								{
                                    field: '${prop.propName}',
                                    title: '<#if prop.note!="">${prop.note}<#else>${prop.propName}</#if>',
                                    width: 100,
                                    filter: true,
                                    sortable: true
                                },
                            </#list>
                        ]]
                    });
                    dg.datagrid('addColumnSearch');
                }
            </script>