<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var name = $('#agentKeyWord').val();
        var rules = [];
        if (name != '' && name != null && name != undefined) {
            var rule = {
                'field': 'name',
                'op': 'lk',
                'data': name
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
    * 专业门类模块
    *
    * */
    jQuery(function ($) {
        var typeStr;
        var rowId;
        var UI = {
            $addBtn: $('#addBtn')
            , $editBtn: $('#editBtn')
            , $deleteBtn: $('#deleteBtn')
            , $cancelBtn: $('#cancelBtn')
            , $submitBtn: $('#submitBtn')
            , $majoredCategoryModal: $('#majoredCategoryModal')
            , $majoredCategoryName: $('#majoredCategoryName')
            , $majoredCategoryType: $('#majoredCategoryType')
        };
        UI.$addBtn.click(function () {
            typeStr = 'add';
            UI.$majoredCategoryModal.modal('show');
        });

        UI.$submitBtn.click(function () {
//                majoredCategoryValidate();
            var categoryName = $.trim(UI.$majoredCategoryName.val());
            var categoryType = $.trim(UI.$majoredCategoryType.val());
            if (categoryName == '') {
                CommonFn.tipsDialog('温馨提示', '学科门类不能为空');
                return false;
            }
            if (categoryName.length > 8) {
                CommonFn.tipsDialog('温馨提示', '学科门类字数不能大于8个字');
                return false;
            }
            if (categoryType == '') {
                CommonFn.tipsDialog('温馨提示', '专业类别不能为空');
                return false;
            }
            var addData = {
                oper: typeStr
                , name: $.trim(UI.$majoredCategoryName.val())
                , majoredList: $.trim(UI.$majoredCategoryType.val())
            };
            CommonFn.getData('/admin/gaokao360/ex/commonsave/${mainObj}', 'post', addData, function (res) {
                if (res.rtnCode == '0000000') {
                    searchLoad();
                    UI.$majoredCategoryModal.modal('hide');
                    UI.$majoredCategoryName.val('');
                    UI.$majoredCategoryType.val('');
                }
            })
        });// 提交添加end
        UI.$cancelBtn.click(function () {
            UI.$majoredCategoryName.val('');
            UI.$majoredCategoryType.val('');
        });





//        修改指定专业门类
        UI.$editBtn.click(function () {
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            UI.$majoredCategoryModal.modal('show');
            // 获取当前行数据
            var rowData = CommonFn.getRowData(rowId);
            console.log(rowData)
            UI.$majoredCategoryName.val(rowData[0].name);
            var dataList = rowData[0].majoredCategoryDTOs;
            var list = '';
                for(var i in dataList){
                    list += dataList[i].name+'、';
                }
            UI.$majoredCategoryType.val(list.substring(0,list.length-1));

        });//修改end


//        删除指定条专业门类
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//        验证函数
//        function majoredCategoryValidate() {
//            var categoryName = $.trim(UI.$majoredCategoryName.val());
//            var categoryType = $.trim(UI.$majoredCategoryType.val());
//            if (categoryName == '') {
//                CommonFn.tipsDialog('温馨提示', '学科门类不能为空');
//                return false;
//            }
//            if (categoryName.length > 8) {
//                CommonFn.tipsDialog('温馨提示', '学科门类字数不能大于8个字');
//                return false;
//            }
//            if (categoryType == '') {
//                CommonFn.tipsDialog('温馨提示', '专业类别不能为空');
//                return false;
//            }
//        }

    });//$  end
</script>