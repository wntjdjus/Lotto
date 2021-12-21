var idx = 1;
var main = {
    // init에 메서드 등록해줘야 사용 가능
    init: function () {
        var _this = this;

        for (var i = 1; i <= 45; i++) {

            var checkbox = `
                        <label><input class="form-check-input" type="checkbox" value="`
                + i +
                `">`
                + i +
                `</label>`;
            var num = document.createElement("div");
            num.setAttribute("class", "form-check form-check-inline");
            num.innerHTML = checkbox;
            const modalBody = document.getElementById("modal-body");

            modalBody.appendChild(num);
        }

        $('#btn-select').on('click', function () {
            _this.select();
        });

        $('#btn-reset').on('click', function () {
            _this.reset();
        });

        $('#lotto').on('click', '[id=btn-save]', function () {
            _this.save(this.parentElement);
        });

    },
    select: function () {

        if (idx == -1) {
            const element = document.getElementById("lotto");
            element.innerHTML = '';
            idx = 1;
        }

        var uri = "/random-lotto";
        var query = "?";
        var isQuery = false;
        const exceptRoundCheck = document.getElementById("exceptRoundCheck");
        if (exceptRoundCheck.value == "Y") {
            const exceptRoundNum = document.getElementById("exceptRoundNum");
            query += "except-round-num=" + exceptRoundNum.options[exceptRoundNum.selectedIndex].value;
            isQuery = true;
        }

        const exceptNumsCheck = document.getElementById('exceptNumsCheck');
        if(exceptNumsCheck.checked == true){
            if(isQuery){
                query += '&';
            }
            query += 'except-nums=' + exceptNumsCheck.value;
            isQuery = true;
        }

        if(isQuery){
            uri += query;
        }

        $.ajax({
            type: 'GET',
            url: uri,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (json) {

            var lotto = JSON.parse(JSON.stringify(json));

            const list = document.getElementById("lotto");

            var node = document.createElement("div");

            var lottoF = `<div class="border border-danger fs-6 align-middle lotto-num">`;
            var lottoE = `</div>`;
            var button = `<button type="button" class="btn btn-outline-primary btn-sm" id="btn-save" class="">저장</button>`;
            node.innerHTML += (lottoF + lotto.num1 + lottoE);
            node.innerHTML += (lottoF + lotto.num2 + lottoE);
            node.innerHTML += (lottoF + lotto.num3 + lottoE);
            node.innerHTML += (lottoF + lotto.num4 + lottoE);
            node.innerHTML += (lottoF + lotto.num5 + lottoE);
            node.innerHTML += (lottoF + lotto.num6 + lottoE);
            node.innerHTML += (button);

            var colapse = `
                <a class="" data-bs-toggle="collapse" href="#lottoCol` + idx + `" role="button" aria-expanded="false" aria-controls="multiCollapseExample1">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-short" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v5.793l2.146-2.147a.5.5 0 0 1 .708.708l-3 3a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L7.5 10.293V4.5A.5.5 0 0 1 8 4z"/>
                </svg>
                </a>
                <div class="row">
                    <div class="col">
                        <div class="collapse multi-collapse" id="lottoCol` + idx + `">
                            <div class="card card-body">
                                <div class="fs-6">해당 번호 과거 당첨 횟수</div>
                                <div class="fs-6">1등 : ` + lotto.pastWin1Cnt + `번</div>
                                <div class="fs-6">2등 : ` + lotto.pastWin2Cnt + `번</div>
                                <div class="fs-6">3등 : ` + lotto.pastWin3Cnt + `번</div>
                                <div class="fs-6">4등 : ` + lotto.pastWin4Cnt + `번</div>
                                <div class="fs-6">5등 : ` + lotto.pastWin5Cnt + `번</div>
                            </div>
                        </div>
                    </div>
                </div>
            `;
            node.innerHTML += colapse;

            list.appendChild(node);
            idx += 1;

        }).fail(function () {
            alert("랜덤로또발췌에러!");
        });

    },
    save: function (parent) {
        if (!this.loginCheck()) {
            return false;
        }
        var child = parent.childNodes;
        var data = {
            "userId": userId,
            "round": nextRound,
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
        }).done(function () {
            child[6].disabled = true;
        }).fail(function () {
            alert("저장에러!");
        });
    },
    reset: function () {

        const element = document.getElementById("lotto");
        element.innerHTML = '';
        idx = 1;
        this.modalReset();
        document.getElementById('exceptRoundCheck').checked = false;
        this.exceptRoundCheckClick();
    },
    loginCheck: function () {
        if (userId == null) {
            alert("로그인 후 가능합니다.");
            return false;
        } else {
            return true;
        }
    },
    modalReset: function () {
        const modalBody = document.getElementById('modal-body');
        const childs = modalBody.children;
        var length = childs.length;
        for (var i = 0; i < length; i++) {
            var checkBox = childs[i].children[0].children[0];
            if (checkBox.checked == true) {
                checkBox.checked = false;
            }
        }

        const exceptNumsCheck = document.getElementById('exceptNumsCheck');
        exceptNumsCheck.checked = false;
    },
    modalConfirm: function () {
        var exceptNums = '';
        const modalBody = document.getElementById('modal-body');
        const exceptNumsCheck = document.getElementById('exceptNumsCheck');
        const childs = modalBody.children;
        var length = childs.length;
        var isChecked = false;
        for (var i = 0; i < length; i++) {
            var checkBox = childs[i].children[0].children[0];
            if (checkBox.checked == true) {
                if(exceptNums != ''){
                    exceptNums += '%20';
                }
                exceptNums += checkBox.value;
                isChecked = true;
            }
        }
        if(isChecked){
            exceptNumsCheck.value = exceptNums;
        }else{
            exceptNumsCheck.checked = false;
        }
    },
    exceptRoundCheckClick: function (){
        const exceptRoundCheck = document.getElementById('exceptRoundCheck');
        const roundList = document.getElementById("exceptRoundNum");
        if (exceptRoundCheck.checked == true) {
            roundList.style.display = "block";
            this.value = "Y";
        } else {
            roundList.style.display = "none";
            this.value = "N";
        }
    },
    exceptNumsCheckClick: function (){
        const exceptNumsCheck = document.getElementById("exceptNumsCheck");
        if (exceptNumsCheck.checked == true) {
            var modal = $('#exceptNumsModal');

            modal.modal('show');
        }else{
            main.modalReset();
        }
    }
};

main.init();

$(document).ready(function () {
    $("input:checkbox").on('click', function () {
        var isChecked = false;
        if ($(this).prop('checked')) {
            isChecked = true;
        }
        if (this.id == 'exceptRoundCheck') {
            main.exceptRoundCheckClick();
        } else if (this.id == 'exceptNumsCheck') {
            main.exceptNumsCheckClick();
        }
    });
    $('#modal-cancel').on('click', function () {
        main.modalReset();
    });
    $('#modal-close').on('click', function () {
        main.modalReset();
    });
    $('#modal-confirm').on('click', function () {
        main.modalConfirm();
    });
});