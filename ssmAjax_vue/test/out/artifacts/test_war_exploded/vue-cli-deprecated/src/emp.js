// new Vue({
//   el: "#emp",
//   data: function () {
//     return {
//       data: [],
//       pkStr: "id",
//       page: 1,
//       inputWidth: 100,
//       endPage: -1,
//       columnName: [],
//     };
//   },
//   mounted() {
//     this.getPage();
//   },
//   methods: {
//     getPage() {
//       $.get("/values/emp?page=" + this.page, (data) => {
//         this.data = data.data;
//         this.pkStr = data.pkStr;
//         this.endPage = data.end;
//         this.columnName = Object.keys(this.data[0]);
//         console.log(data);
//
//         this.inputWidth =
//           ($(window).width * 0.8 - (Object.keys(data[0]).length + 1) * 20) /
//           (Object.keys(data[0]).length + 1);
//       });
//     },
//     search() {
//       $.ajax({
//         type: "GET",
//         url: "/values/emp",
//         data: $("#form").serializeArray(),
//         success: (data) => {
//           this.data = data.data;
//           this.pkStr = data.pkStr;
//           this.end = data.end;
//         },
//       });
//     },
//     deleteRow(pk) {
//       $.get("return/delete/" + pk, (data) => {
//         $("#re").html(data);
//         setTimeout(() => {
//           $("#re").html(""); // 隐藏元素
//         }, 3000); // 3000毫秒（即3秒）
//
//         this.getPage();
//       });
//     },
//     insertRow() {
//       $.ajax({
//         type: "GET",
//         url: "/return/insert",
//         data: $("#formInsert").serializeArray(),
//         success: (data) => {
//           $("#re").html(data);
//           setTimeout(() => {
//             $("#re").html(""); // 隐藏元素
//           }, 3000); // 3000毫秒（即3秒）
//
//           this.getPage();
//         },
//       });
//     },
//     home() {
//       this.page = 1;
//       this.getPage();
//     },
//     up() {
//       this.page = this.page == 0 ? 0 : --this.page;
//       this.getPage();
//     },
//     next() {
//       this.page = this.page == this.endPage ? this.endPage : ++this.page;
//       this.getPage();
//     },
//     footer() {
//       this.page = this.endPage;
//       this.getPage();
//     },
//   },
// });
