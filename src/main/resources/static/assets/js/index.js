var main = {
    // init에 메서드 등록해줘야 사용 가능
    init : function (){
        var _this = this;

        $('#btn-select').on('click', function (){
            _this.select();
        });

        $('#btn-save').on('click', function (){
            _this.save();
        });

        $('#btn-update').on('click', function (){
            _this.update();
        });

        $('#btn-delete').on('click', function (){
            _this.delete();
        });

        $('#btn-reset').on('click', function (){
            _this.reset();
        });
    },
    select : function (){

        $.ajax({
            type: 'GET',
            url: '/random',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (json){

            var lotto = JSON.parse(JSON.stringify(json));

            const element = document.getElementById("lotto");

            element.innerHTML+=('<button type="button" style="margin-left:0em">'+lotto.num1+'</button>');
            element.innerHTML+=('<button type="button" style="margin-left:0em">'+lotto.num2+'</button>');
            element.innerHTML+=('<button type="button" style="margin-left:0em">'+lotto.num3+'</button>');
            element.innerHTML+=('<button type="button" style="margin-left:0em">'+lotto.num4+'</button>');
            element.innerHTML+=('<button type="button" style="margin-left:0em">'+lotto.num5+'</button>');
            element.innerHTML+=('<button type="button" style="margin-left:0em">'+lotto.num6+'</button>');
            element.innerHTML+=('<button type="button" style="margin-left:0em">'+lotto.extraNum+'</button>');
            element.innerHTML+='<br/>';

        }).fail(function (error){
            alert(JSON.stringify(error));
        });

    },
    save : function (){
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('글이 등록되었습니다.');
            window.location.href = '/';
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
    }
};

main.init();