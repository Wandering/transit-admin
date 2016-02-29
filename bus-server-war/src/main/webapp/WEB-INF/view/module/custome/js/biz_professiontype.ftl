<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var professionType = $('#title').val();
        console.log(professionType)
        var rules = [];
        if (professionType != '' && professionType != null && professionType != undefined) {
            var rule = {
                'field': 'queryparam',
                'op': 'lk',
                'data': professionType
            }
            rules.push(rule);
        }
        return rules;
    }


    $("#search").click(function () {
        searchLoad();

    });


    /*
    *
    * 职业分类模块开发
    *
    * */

    jQuery(function ($) {
        var typeStr, rowId;
        var UI = {
            $addBtn: $('#addBtn')
            , $editBtn: $('#editBtn')
            , $deleteBtn: $('#deleteBtn')
        };




//        添加
        UI.$addBtn.on(ace.click_event, function () {
            typeStr = 'add';
            bootbox.dialog({
                title: "添加职业分类",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback": addAndEditFun
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
        });
//        修改
        UI.$editBtn.on(ace.click_event, function () {
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            bootbox.dialog({
                title: "修改职业分类",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback": addAndEditFun
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
            // 获取当前行数据
            var rowData = CommonFn.getRowData(rowId);
            console.log(rowData)
            $('#professionType').val(rowData[0].professionType);
            var dataList = rowData[0].childs;
            var list = '';
            for(var i=0;i<dataList.length;i++){
                list += dataList[i].professionType+'、';
            }
            $('#content').val(list);
        });

//      删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//          添加修改方法
        function addAndEditFun() {
//            验证
            var $professionType = $.trim($('#professionType').val());
            if ($professionType.length == '') {
                CommonFn.tipsDialog('温馨提示', '行业名称不能为空');
                return false;
            }
            if ($professionType.length > 8) {
                CommonFn.tipsDialog('温馨提示', '行业名称不能大于8个字');
                return false;
            }
            var $content = $.trim($('#content').val());
            if ($content.length == '') {
                CommonFn.tipsDialog('温馨提示', '职业分类不能为空');
                return false;
            }
            var data = {
                oper: typeStr,
                professionType: $('#professionType').val(),
                content: $('#content').val()
            };
            if(typeStr=='edit'){
                data.id=rowId;
            }
            CommonFn.getData('/admin/gaokao360/ex/commonsave/${mainObj}', 'post', data, function (res) {
                if (res.rtnCode == '0000000') {
                    searchLoad();
                }
            })
        }

//        dialog弹框
        var dialogHtml = ''
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<form class="form-horizontal" role="form">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="professionType"> 行业名称：</label>'
                + '<div class="col-sm-5">'
                + '<input type="text" placeholder="行业名称最多不能超过8个字" id="professionType" class="col-sm-9">'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="content"> 职业分类：</label>'
                + '<div class="col-sm-9">'
                + '<textarea  id="content"  rows="5" class="col-sm-12" placeholder="注意:职业分类之间必须以顿号相隔、"></textarea>'
                + '</div>'
                + '</div>'
                + '</form>'
                + '</div>'
                + '</div>';
    });//$ end


</script>