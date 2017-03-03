package grailsimportexcel

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import static org.apache.poi.ss.usermodel.Cell.*
import java.io.File

class ExcelImporterController {

    def index() { }

    def uploadFile() {
      def file = request.getFile('excelFile')
      if(!file.empty) {
        def sheetheader = []
        def values = []
        def workbook = new XSSFWorkbook(file.getInputStream())
        def sheet = workbook.getSheetAt(0)

        for (cell in sheet.getRow(0).cellIterator()) {
          sheetheader << cell.stringCellValue
        }

        def headerFlag = true
        for (row in sheet.rowIterator()) {
            if (headerFlag) {
                headerFlag = false
                continue
            }
            def value = ''
            def map = [:]
            for (cell in row.cellIterator()) {
                switch(cell.cellType) {
                    case 1:
                        value = cell.stringCellValue
                        map["${sheetheader[cell.columnIndex]}"] = value
                        break
                    case 0:
                        value = cell.numericCellValue
                        map["${sheetheader[cell.columnIndex]}"] = value
                        break
                    default:
                        value = ''
                }
            }
            values.add(map)
        }

        values.each { v ->
          if(v) {
            Subscriber.findByEmail(v.email)?: new Subscriber(email:v.email,fullname:v.fullname).save(flush:true, failOnError:true)
          }
        }

        flash.message = "Subscriber imported successfully"
        redirect action:"index"
      }
    }
}
