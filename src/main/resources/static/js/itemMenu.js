/*<![CDATA[*/
$(document).ready(function() {

    var mMenu = /*[[${entitys.menu}]]*/
    if(mMenu != "" || mMenu != null){
        var itemMenu = mMenu.split(";");
        var i = 1;
        var value;

        itemMenu.forEach(function(item, index, array){
            value = (item == "1")? true: false;
            $('#Jogo' + i).prop("checked",value);
            i++;
        });
    }

    $("#botaoSessao").click(function() {
        var menu = "";
        var NumJogos = 65;
        for(i=1; i< NumJogos; i++){
            if($('#Jogo'+ i).is(':checked')){
                menu = menu + 1 + ";";
            }
            else{
                menu = menu + 0 + ";";
            }
        }
        if($('#JogoBonus').is(':checked')){
            menu = menu + 1;
        }
        else{
            menu = menu + 0;
        }
        $("#itemMenu").val(menu);

        document.configForm.submit();
    });
});
/*]]>*/