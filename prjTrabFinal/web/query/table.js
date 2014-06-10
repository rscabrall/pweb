function tableHighlightRow() {
  if (document.getElementById && document.createTextNode) {
    var tables=document.getElementsByTagName('table');
    for (var i=0;i<tables.length;i++)
    {
      if(tables[i].className=='hilite') {
        var trs=tables[i].getElementsByTagName('tr');
        for(var j=0;j<trs.length;j++)
        {
          if(trs[j].parentNode.nodeName=='tbody') {
            trs[j].onmouseover=function(){this.className='hilight';return false}
            trs[j].onmouseout=function(){this.className='';return false}
          }
        }
      }
    }
  }
}