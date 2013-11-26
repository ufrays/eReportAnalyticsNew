package org.sap.era.service.excel;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import org.sap.era.service.excel.SVFractionalFormat;

public class CellFormatter {
	private Format[] textFormatter;

    private DecimalFormat generalNumberFormat = new DecimalFormat("0");

    public CellFormatter() {
      textFormatter = new Format[0x31];

      textFormatter[0x01] = new DecimalFormat("0");
      textFormatter[0x02] = new DecimalFormat("0.00");
      textFormatter[0x03] = new DecimalFormat("#,##0");
      textFormatter[0x04] = new DecimalFormat("#,##0.00");
      textFormatter[0x05] = new DecimalFormat("$#,##0;$#,##0");
      textFormatter[0x06] = new DecimalFormat("$#,##0;$#,##0");
      textFormatter[0x07] = new DecimalFormat("$#,##0.00;$#,##0.00");
      textFormatter[0x08] = new DecimalFormat("$#,##0.00;$#,##0.00");
      textFormatter[0x09] = new DecimalFormat("0%");
      textFormatter[0x0A] = new DecimalFormat("0.00%");
      textFormatter[0x0B] = new DecimalFormat("0.00E0");
      textFormatter[0x0C] = new SVFractionalFormat("# ?/?");
      textFormatter[0x0D] = new SVFractionalFormat("# ??/??");
      textFormatter[0x0E] = new SimpleDateFormat("M/d/yy");
      textFormatter[0x0F] = new SimpleDateFormat("d-MMM-yy");
      textFormatter[0x10] = new SimpleDateFormat("d-MMM");
      textFormatter[0x11] = new SimpleDateFormat("MMM-yy");
      textFormatter[0x12] = new SimpleDateFormat("h:mm a");
      textFormatter[0x13] = new SimpleDateFormat("h:mm:ss a");
      textFormatter[0x14] = new SimpleDateFormat("h:mm");
      textFormatter[0x15] = new SimpleDateFormat("h:mm:ss");
      textFormatter[0x16] = new SimpleDateFormat("M/d/yy h:mm");
      // 0x17 - 0x24 reserved for international and undocumented 0x25, "(#,##0_);(#,##0)"
      //start at 0x26
      //jmh need to do colour
      //"(#,##0_);[Red](#,##0)"
      textFormatter[0x26] = new DecimalFormat("#,##0;#,##0");
      //jmh need to do colour
      //(#,##0.00_);(#,##0.00)
      textFormatter[0x27] = new DecimalFormat("#,##0.00;#,##0.00");
      textFormatter[0x28] = new DecimalFormat("#,##0.00;#,##0.00");
      //textFormatter[0x29] = new DecimalFormat("_(*#,##0_);_(*(#,##0);_(* \"-\"_);_(@_)");
      //textFormatter[0x2A] = new DecimalFormat("_($*#,##0_);_($*(#,##0);_($* \"-\"_);_(@_)");
      //textFormatter[0x2B] = new DecimalFormat("_(*#,##0.00_);_(*(#,##0.00);_(*\"-\"??_);_(@_)");
      //textFormatter[0x2C] = new DecimalFormat("_($*#,##0.00_);_($*(#,##0.00);_($*\"-\"??_);_(@_)");
      textFormatter[0x2D] = new SimpleDateFormat("mm:ss");
      textFormatter[0x2E] = new SimpleDateFormat("[h]:mm:ss");
      textFormatter[0x2F] = new SimpleDateFormat("mm:ss.0");
      textFormatter[0x30] = new DecimalFormat("##0.0E0");
    }

    public String format(short index, Object value) {
      if (index == 0)
        return value.toString();
      if (textFormatter[index] == null)
        throw new RuntimeException("Sorry. I cant handle the format code :"+Integer.toHexString(index));
      return textFormatter[index].format(value);
    }

    public String format(short index, double value) {
      if ( index <= 0 )
        return generalNumberFormat.format(value);
      if (textFormatter[index] == null)
        throw new RuntimeException("Sorry. I cant handle the format code :"+Integer.toHexString(index));
      if (textFormatter[index] instanceof DecimalFormat) {
        return ((DecimalFormat)textFormatter[index]).format(value);
      }
      if (textFormatter[index] instanceof SVFractionalFormat) {
        return ((SVFractionalFormat)textFormatter[index]).format(value);
      }
      throw new RuntimeException("Sorry. I cant handle a non decimal formatter for a decimal value :"+Integer.toHexString(index));
    }

    public boolean useRedColor(short index, double value) {
      return (((index == 0x06)||(index == 0x08)||(index == 0x26) || (index == 0x27)) && (value < 0));
    }
}
