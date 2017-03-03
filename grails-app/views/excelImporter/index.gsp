<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Excel Importer</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>

    <div id="content" role="main">
        <section class="row colset-2-its">
            <h2>Excel Importer</h2>
            <g:if test="${flash.message}">
              <div class="message" role="alert">
                ${flash.message}
              </div>
            </g:if>
            <g:uploadForm action="uploadFile" >
              <fieldset>
                <div class="fieldcontain">
                  <input type="file" name="excelFile" />
                </div>
              </fieldset>
              <fieldset>
                <g:submitButton name="uploadbutton" class="save" value="Upload" />
              </fieldset>
            </g:uploadForm>
        </section>
    </div>

</body>
