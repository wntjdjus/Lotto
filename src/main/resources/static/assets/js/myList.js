var idx = 1;
var main = {
    // init에 메서드 등록해줘야 사용 가능
    init: function () {

        var _this = this;
        const roundList = document.getElementById("my-lotto-round");
        for (var i = recentRound; i > 0; i--) {
            var node = document.createElement("option");
            node.setAttribute("value", i);
            node.textContent = i+'회';
            if (i == recentRound) {
                node.selected = true;
            }
            roundList.appendChild(node);
        }
        _this.changeRound();

        $('#btn-reset').on('click', function () {
            _this.reset();
        });

        $('#lotto').on('click', '[id=btn-delete]', function () {
            _this.delete(this.parentElement);
        });


        $('#my-lotto-round').change(function (){
            _this.changeRound();
        });
    },
    delete: function (parent) {
        var roundSelect = document.getElementById("my-lotto-round");
        var round = roundSelect.options[roundSelect.selectedIndex].value;
        var deleteId = parent.getAttribute("value");

        $.ajax({
            type: 'DELETE',
            url: '/userlotto/' + deleteId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            parent.innerHTML = '';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    reset: function () {

        const element = document.getElementById("lotto");
        element.innerHTML = '';
        idx = 1;
    },
    loginCheck: function () {
        if (userId == null) {
            alert("로그인 후 가능합니다.");
            return false;
        } else {
            return true;
        }
    },
    changeRound : function (){
        if (!this.loginCheck()) {
            return false;
        }

        this.reset();
        var roundSelect = document.getElementById("my-lotto-round");
        var round = roundSelect.options[roundSelect.selectedIndex].value;

        $.ajax({
            type: 'GET',
            url: '/userlottos/' + round,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (json) {
            var lottos = JSON.parse(JSON.stringify(json));
            var l = lottos.length;
            const list = document.getElementById("lotto");

            for (var i = 0; i < l; i++) {
                var lotto = lottos[i];
                var node = document.createElement("div");
                node.setAttribute("value", lotto.id);
                var lottoF = `<div class="border border-danger fs-6 align-middle lotto-num">`;
                var lottoE = `</div>`;
                var button = `<button type="button" class="btn btn-outline-danger btn-sm" id="btn-delete" class="">삭제</button>`;
                var rankF = `<button type="button" class="btn btn-outline-primary btn-sm" id="" class="" disabled>`;
                var rankE = `</button>`;
                node.innerHTML += (lottoF + lotto.num1 + lottoE);
                node.innerHTML += (lottoF + lotto.num2 + lottoE);
                node.innerHTML += (lottoF + lotto.num3 + lottoE);
                node.innerHTML += (lottoF + lotto.num4 + lottoE);
                node.innerHTML += (lottoF + lotto.num5 + lottoE);
                node.innerHTML += (lottoF + lotto.num6 + lottoE);
                node.innerHTML += (button);
                if(lotto.rank != 0){
                    node.innerHTML += (rankF + lotto.rank + '등!' + rankE);
                }

                list.appendChild(node);
                idx += 1;
            }

            idx = -1;
        }).fail(function (json) {
            alert(JSON.stringify(json));
        })
    }
};

main.init();