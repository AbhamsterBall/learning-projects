// function loadDoc() {
//     var xhttp = new XMLHttpRequest();
//     xhttp.onreadystatechange = function() {
//         if (this.readyState == 4 && this.status == 200) {
//             document.getElementById("demo").innerHTML = this.responseText;
//         }
//     };
//     xhttp.open("GET", "/values/text", true);
//     xhttp.send();
// }
// function loadDoc() {
//     $.get("/values/text", function(data) {
//         $("#demo").html(data);
//     });
// }
new Vue({
    el: '#index',
    data: {
        data: [],
        pageLength: -1,
        page: 1,
        inputWidth: 100,
        primaryKey: "id"
    },
    mounted() {
        this.getPage();
        // this.getTable();
    },
    methods: {
        getPage() {
            // 发起Ajax请求，获取数据
            // 替换以下示例数据
            $.get("values/table?page=" + this.page, (data) => {
                console.log(data)
                let response = data
                this.data = response.data;
                this.pageLength = response.pageLength;
                this.primaryKey = response.primaryKey;
                this.inputWidth = ($(window).width() * 0.8 - (Object.keys(this.data[0]).length + 1) * 20) / (Object.keys(this.data[0]).length + 1)
            })
        },
        getTable() {
            console.log($("#form").serializeArray())
               $.ajax({
                type: "GET", // 或 "GET"，取决于你的需求
                url: "values/table", // 表单数据处理的URL
                data: $("#form").serializeArray(), // 序列化表单数据
                success: (data) => {
                    let response = data
                    this.data = response.data;
                    this.pageLength = response.pageLength;
                    this.primaryKey = response.primaryKey;

                    console.log(this.data)
                },
                error: (error) => {
                    console.log(error);
                }
            });
        },
        deleteItem(pk) {
            $.get("return/delete/" + pk, (data) => {
                // let response = data /*暂存量，才可以输出，否则会直接从网页拉取而不显示*/
                // // console.log(data)
                $("#re").html(data)
                setTimeout(() => {
                    $("#re").html("") // 隐藏元素
                }, 3000); // 3000毫秒（即3秒）

                this.getPage()
            })
        },
        header() {
            this.page = 1;
            this.getPage();
        },
        up() {
            this.page = this.page - 1 < 1 ? 1 : this.page - 1;
            this.getPage();
        },
        next() {
            this.page = this.pageLength < this.page + 1 ? this.pageLength : this.page + 1;
            this.getPage();
        },
        footer() {
            this.page = this.pageLength;
            this.getPage();
        },
    }
});
// ajax
// let pageLength = -1
// function getData(data) {
//     let table = $('#dataTable')
//     table.empty()
//     console.log(data)
//
//     let head = false
//     $.each(data['data'], function (index, item) {
//         let row = $('<tr>');
//         if (!head) {
//             let rowH = $('<tr>')
//             for (let key in item) {
//                 if (item[key] == null) {
//                     $('<th>').text(key).appendTo(rowH)
//                     console.log(key)
//                 } else {
//                     $('<th>').text(key).appendTo(rowH)
//                 }
//             }
//             head = true
//
//             rowH.appendTo(table)
//
//             let form = $("#form")
//             form.empty()
//             for (let key in item) {
//                 let input = $('<input>')
//                 let width = ($(window).width() * 0.8 - (Object.keys(item).length + 1) * 20) / (Object.keys(item).length + 1)
//                 // let left = ($(window).width() - ((width + 30) * (Object.keys(item).length))) / 2
//                 input.attr({
//                     "type" : "text",
//                     "name" : key,
//                     "value" : "${key}",
//                     "placeholder" : key,
//                     "style" : "width:" + width + "px"
//                 })
//                 // form.attr({"style" : "margin-left:" + left + "px"})
//
//                 input.appendTo(form)
//             }
//
//             let submit = $('<input>')
//             submit.attr({
//                 "type" : "submit",
//                 "value" : "search"
//             })
//
//             submit.appendTo(form)
//
//         }
//         for (let key in item) {
//             if (item[key] == null) $('<td>').text("N/A").appendTo(row)
//             else
//                 $('<td>').text(item[key]).appendTo(row);
//
//         }
//         let updateHref = $('<a>').text('update')
//         updateHref.attr('href', '#')
//         let deleteHref = $('<a>').text('delete')
//         deleteHref.attr('href', "#")
//         $('<td>').append(updateHref).appendTo(row);
//         $('<td>').append(deleteHref).appendTo(row);
//
//         console.log(row)
//         row.appendTo(table)
//     })
//     pageLength = data['pageLength']
// }
// let page = 1
// function getPage() {
//
//     // $.ajax({
//     //     type: "GET", // 或 "GET"，取决于你的需求
//     //     url: "/values/table?page=" + page, // 表单数据处理的URL
//     //     data: $(this).serialize(), // 序列化表单数据
//     //     success: function (response) {
//     //         getData(response)
//     //     },
//     //     error: function (error) {
//     //         console.log(error);
//     //     }
//     // });
//     $.get("values/table?page=" + page, function (data) {
//         getData(data)
//     })
// }
// getPage()
// function header() {
//     page = 1
//     getPage()
// }
// function up() {
//     page = page - 1 < 1 ? 1 : --page
//     getPage()
// }
// function next() {
//     page = pageLength < page + 1 ? pageLength : ++page
//     getPage()
// }
// function footer() {
//     page = pageLength
//     getPage()
// }
// $(document).ready(function () {
//     $("#form").submit(function (event) {
//         event.preventDefault(); // 阻止默认表单提交
//
//         $.ajax({
//             type: "GET", // 或 "GET"，取决于你的需求
//             url: "values/table", // 表单数据处理的URL
//             data: $(this).serialize(), // 序列化表单数据
//             success: function (response) {
//                 // $("#result").html(response); // 将响应插入到指定的区域
//                 getData(response)
//             },
//             error: function (error) {
//                 console.log(error);
//             }
//         });
//
//     });
// });
