<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
  <head>
    <title><tiles:getAsString name="title"/></title>
  </head>
  <body>
    <table width="90%" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2" align="center" style='background-color: maroon; color: white'>
          <tiles:insertAttribute name="header" />
        </td>
      </tr>
      <tr>
        <td>
          <tiles:insertAttribute name="body" />
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center" style='background-color: maroon; color: white;' >
          <tiles:insertAttribute name="footer" />
        </td>
      </tr>
    </table>
  </body>
</html>