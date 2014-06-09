<%-- 
    Document   : AlunoConsulta
    Created on : 27/05/2014, 08:36:50
    Author     : Rafael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <style type="text/css">
            table { margin: 1em; border-collapse: collapse; } 
            td, th { padding: .3em; border: 1px #ccc solid; } 
            thead { background: #fc9; } 
            tbody { background: #9cf; } 
            #highlight tr.hilight { background: #c9f; }
            
        </style>
        <script src="query/jquery-2.1.1.js"></script>        
        <script>            
            function muda(){
                //window.alert("Teste");
                //window.alert("Muda");   
                
                
                window.alert("Classe: " + classe);
                $.get('Controller', {campo:campo, valor:valor, classe:classe , metodo:metod},
                    function(responseText){
                        $('#result').html(responseText);
                    } );
                                                   
            }
            function envia(){
                                
                var campo = $('#campo').val(); 
                //window.alert(campo);
                var valor = $('#valor').val();
                var classe = $('#classe').val();
                var metod = $('#metodo').val(); 
                //var teste = "Rafael";
                //window.alert(campo);
                $.get('Controller', {campo:campo, valor:valor, classe:classe , metodo:metod},
                    function(responseText){
                        $('#result').html(responseText);
                    } );
                    
                //window.alert(campo);    
            }
            
            function tableHighlightRow() 
            {  
                if (document.getElementById && document.createTextNode) {    
                    var tables=document.getElementsByTagName('table');    
                    for (var i=0;i<tables.length;i++)    
                    {      
                        if(tables[i].className=='hilite') {
                            var trs=tables[i].getElementsByTagName('tr');        
                            for(var j=0;j<trs.length;j++) {          
                                    if(trs[j].parentNode.nodeName=='TBODY') {            
                                            trs[j].onmouseover=function(){this.className='hilight';return false}            
                                            trs[j].onmouseout=function(){this.className='';return false}          
                                    }        
                            }      
                        }    
                    }  
                } 
            } 
window.onload=function(){tableHighlightRow();}
            
        </script>        
        <title>Aluno - Consultar</title>
    </head>                       
            <select id="campo">
                <option>Id</option>
                <option>Nome</option>
                <option>RG</option>
                <option>CPF</option>
            </select>
            
            <input type="text" id="valor" />
            <input type="hidden" id="classe" value="AlunoLogica" />
            <input type="hidden" id="metodo" value="consultar" />            
            <input type="button" value="Consultar" name="Consultar" onclick="javascript:envia()" />
                    
        <br>
         <div id="result">
         </div>
    </body>
</html>
