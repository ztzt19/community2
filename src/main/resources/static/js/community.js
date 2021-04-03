/**
 * 提交回复
 */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2Target(questionId, 1, content);
    // if (!content){
    //     alert("不能回复空内容~~~");
    //     return;
    // }
    // $.ajax({
    //     type: "POST",
    //     url: "/comment",
    //     contentType:'application/json',
    //     data: JSON.stringify({
    //         "parentId":questionId,
    //         "content":content,
    //         "type":1
    //     }),
    //     success: function (response) {
    //         if (response.code == 200){
    //             // $("#comment_section").hide();
    //             window.location.reload();
    //         }else {
    //             if (response.code == 2003){
    //                 var isAccepted = confirm(response.message);
    //                 if (isAccepted){
    //                     window.open("https://github.com/login/oauth/authorize?client_id=a552a2487e5816eb7fe0&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
    //                     window.localStorage.setItem("closable",true);
    //                 }
    //             }else{
    //                 alert(response.message);
    //             }
    //         }
    //         console.log(response);
    //     },
    //     dataType: "json"
    // });
}

function comment2Target(targetId, type, content) {
    // var questionId = $("#question_id").val();
    // var content = $("#comment_content").val();
    if (!content) {
        alert("不能回复空内容~~~");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            // "parentId":questionId,
            "parentId": targetId,
            "content": content,
            // "type":1
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                // $("#comment_section").hide();
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=a552a2487e5816eb7fe0&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
            console.log(response);
        },
        dataType: "json"
    });
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2Target(commentId, 2, content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    //获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        //折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        //最后一个元素（回复的框），！=1，说明我们已经加载过了，之间展示就行
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            //标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {    //去重新加载。（如果加载过了就不能重新去调用请求了）
            $.getJSON("/comment/" + id, function (data) {
                // console.log(data);
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-circle",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu",
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                //标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }

    }
    // console.log(id);
}

function showSelectTag() {
    $("#select-tag").show();
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1){
        if (previous){
            $("#tag").val(previous + "," + value);
        }else {
            $("#tag").val(value);
        }
    }
}
