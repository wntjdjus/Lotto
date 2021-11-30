var idx = 1;
var main = {
    // init에 메서드 등록해줘야 사용 가능
    init : function (){
        var _this = this;

        $('#btn-select').on('click', function (){
            _this.select();
        });

        // $('#btn-save').on('click', function (){
        //     _this.save();
        // });

        $('#btn-update').on('click', function (){
            _this.update();
        });

        $('#btn-delete').on('click', function (){
            _this.delete();
        });

        $('#btn-reset').on('click', function (){
            _this.reset();
        });

        $('#btn-api').on('click', function (){
            _this.api();
        });

        $('#lotto').on('click', '[id=btn-save]', function (){
           _this.save(this.parentElement.id);
        });
    },
    select : function (){

        if(idx == -1){
            const element = document.getElementById("lotto");
            element.innerHTML = '';
            idx = 1;
        }

        $.ajax({
            type: 'GET',
            url: '/random-lotto',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (json){

            var lotto = JSON.parse(JSON.stringify(json));

            const list = document.getElementById("lotto");

            var node = document.createElement("div");
            node.id = "lotto"+idx;
            node.innerHTML+=('<button type="button" name="num" style="margin-left:0em">'+lotto.num1+'</button>');
            node.innerHTML+=('<button type="button" name="num" style="margin-left:0em">'+lotto.num2+'</button>');
            node.innerHTML+=('<button type="button" name="num" style="margin-left:0em">'+lotto.num3+'</button>');
            node.innerHTML+=('<button type="button" name="num" style="margin-left:0em">'+lotto.num4+'</button>');
            node.innerHTML+=('<button type="button" name="num" style="margin-left:0em">'+lotto.num5+'</button>');
            node.innerHTML+=('<button type="button" name="num" style="margin-left:0em">'+lotto.num6+'</button>');
            if(userId != null){
                node.innerHTML+=('<button type="button" id="btn-save" style="margin-left:0em">저장</button>');
            }
            node.innerHTML+='<br/>';

            list.appendChild(node);
            idx += 1;

        }).fail(function (error){
            alert(JSON.stringify(error));
        });

    },
    save : function (id){

        var round = document.getElementById("lotto-round").textContent;
        var lotto = document.getElementById(id);
        var child = lotto.childNodes;
        var data = {
            "userId": userId,
            "round": round,
            "num1": child[0].textContent,
            "num2": child[1].textContent,
            "num3": child[2].textContent,
            "num4": child[3].textContent,
            "num5": child[4].textContent,
            "num6": child[5].textContent
        };

        $.ajax({
            type: 'POST',
            url: '/userlotto',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('랜덤로또번호 저장!');
            child[6].disabled = true;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    update : function (){
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.');
            window.location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    reset : function (){

        const element = document.getElementById("lotto");
        element.innerHTML = '';
        idx = 1;
    },
    api : function (){
        $.ajax({
            type: 'GET',
            url: '/api',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (json){
            alert(JSON.stringify(json));
        }).fail(function (json){
            alert(JSON.stringify(json));
        })
    }
};

main.init();