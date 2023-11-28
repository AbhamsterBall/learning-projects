new Vue({
    el: '#update',
    data: {
        entity : {},
        primaryKey : "id",
        primaryValue : 1,
        re: ""
    },
    mounted() {
        this.getEntity();
    },
    methods: {
        getEntity() {
            $.get("/values/entity/", data => {
                let response = data
                this.entity = response.entity
                this.primaryKey = response.primaryKey
                this.primaryValue = this.entity.primaryKey

                $(() => {
                    $("#" + this.primaryKey).prop("readonly", true)
                    $("#" + this.primaryKey).prop('style', "border-bottom:0px")
                })

            })

        },
        updateEmp() {
            $.ajax({
                type: "GET",
                url: "/return/update",
                data: $("#form").serializeArray(),
                success: data => {
                    this.re = data
                    $("#re").html(this.re)
                },
                error: error => {
                    console.log(error)
                }
            })
        }
    }
})