/**
 * Created by TangJiong on 2015/12/28.
 * core ajax based js file
 */
// 控制是否显示调试信息
var DEBUG = true;

$(function(){

    var initKnowledgeNulLevel =  function(data){

        var level1Knowledge = [];
        var level2Knowledge = [];
        var level3Knowledge = [];

        // 先按知识点的层级分别存放到不同层的数组里面
        for (var i = 0, len = data.length; i<len; i++) {

            var knowledge = data[i];

            switch (knowledge.level) {
                case 1:
                    level1Knowledge.push(knowledge);
                    break;
                case 2:
                    level2Knowledge.push(knowledge);
                    break;
                case 3:
                    level3Knowledge.push(knowledge);
                    break;
                default:
                    break;
            }

        }

        var knowledgeUl = $("ul.mul-level-select");
        // 遍历一级知识点
        for (var j = 0, l1len = level1Knowledge.length; j<l1len; j++) {

            // 一级知识点
            var l1Knowledge = level1Knowledge[j];
            var l1li = $("<li/>", {
                class: "list-group-item",
                id: l1Knowledge.id
            });

            var l1HasChildren = false;
            var l2ul = $("<ul/>", {
                class: "list-group nav-second-level"
            });

            // 遍历二级知识点，从中找一级知识点的child
            for (var k = 0, l2len = level2Knowledge.length; k<l2len; k++) {

                // 二级知识点
                var l2Knowledge = level2Knowledge[k];

                if(l2Knowledge.parentId == l1Knowledge.id) {

                    var l2li = $("<li/>", {
                        class: "list-group-item",
                        id: l2Knowledge.id
                    });


                    var l2HasChildren = false;
                    var l3ul = $("<ul/>", {
                        class: "list-group nav-third-level"
                    });

                    // 遍历三级知识点，从中找出二级知识点的child
                    for(var l = 0, l3len = level3Knowledge.length; l<l3len; l++) {

                        // 三级知识点
                        var l3Knowledge = level3Knowledge[l];

                        if(l3Knowledge.parentId == l2Knowledge.id) {
                            var l3li = $("<li/>", {
                                class: "list-group-item",
                                id: l3Knowledge.id
                            });
                            var l3a = $("<a/>", {
                                href: "javascript:;",
                                text: l3Knowledge.name
                            });
                            l3a.appendTo(l3li);
                            l3li.appendTo(l3ul);

                            l2HasChildren = true;
                        }

                    }

                    if(l2HasChildren){
                        l2li.append(''
                            +   '<a href="javascript:;">'
                            +       l2Knowledge.name + '<span class="fa arrow"></span>'
                            +   '</a>'
                        );
                        l3ul.appendTo(l2li);
                    }else{
                        var l2a = $("<a/>", {
                            href: "javascript:;",
                            text: l2Knowledge.name
                        });
                        l2a.appendTo(l2li);
                    }

                    l2li.appendTo(l2ul);

                    l1HasChildren = true;
                }

            }

            if(l1HasChildren){
                l1li.append(''
                    +   '<a href="javascript:;">'
                    +       '<i class="fa fa-sitemap fa-fw"></i>&nbsp;'+ l1Knowledge.name + '<span class="fa arrow"></span>'
                    +   '</a>'
                );
                l2ul.appendTo(l1li);
            }else {
                l1li.append(''
                    +   '<a href="javascript:;">'
                    +       '<i class="fa fa-sitemap fa-fw"></i>&nbsp;'+ l1Knowledge.name
                    +   '</a>'
                );
            }

            l1li.appendTo(knowledgeUl);
        }

        // 调用插件函数
        knowledgeUl.metisMenu();

    };

    ajaxCourseKnowledge(1,initKnowledgeNulLevel);

});

function ajaxCourseKnowledge(courseId, callback){

    $.ajax({
        type: "GET",
        url: "/knowledge/course/"+courseId,
        dataType: "json",
        complete: function(XMLHttpRequest, textStatus){
            if(DEBUG){
                console.log(textStatus);
            }
        },
        success: function(data, textStatus){
            if(DEBUG){
                console.log(textStatus);
            }
            callback(data);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            if(DEBUG){
                console.log(textStatus + " " +  errorThrown);
            }
        }
    });

}
