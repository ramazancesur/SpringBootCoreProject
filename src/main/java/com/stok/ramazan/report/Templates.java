package com.stok.ramazan.report;

import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;
import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.builder.style.BorderBuilder;
import net.sf.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.Markup;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang.time.DateFormatUtils;
import org.jsoup.safety.Whitelist;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.tableOfContentsCustomizer;
import static net.sf.dynamicreports.report.builder.DynamicReports.template;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Templates {
  public static final StyleBuilder rootStyle;
  public static final StyleBuilder boldStyle;
  public static final StyleBuilder italicStyle;
  public static final StyleBuilder boldCenteredStyle;
  public static final StyleBuilder bold12CenteredStyle;
  public static final StyleBuilder bold18CenteredStyle;
  public static final StyleBuilder bold22CenteredStyle;
  public static final StyleBuilder columnStyle;
  public static final StyleBuilder columnTitleStyle;
  public static final StyleBuilder groupStyle;
  public static final StyleBuilder subtotalStyle;

  public static final StyleBuilder htmlMarkupStyle;
  public static final org.jsoup.safety.Whitelist wl;

  public static final ReportTemplateBuilder reportTemplate;
  public static final CurrencyType currencyType;
  public static final ComponentBuilder<?, ?> dynamicReportsComponent;
  public static final ComponentBuilder<?, ?> footerComponent;

  public static final BorderBuilder border;
  public static final StyleBuilder generalBorderStyle;
  public static final ConditionalStyleBuilder lastRow;
  private static final ImageBuilder image;

  static {
    rootStyle = stl.style().setPadding(2);
    boldStyle = stl.style(rootStyle).bold();
    italicStyle = stl.style(rootStyle).italic();
    boldCenteredStyle = stl.style(boldStyle).setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
    bold12CenteredStyle = stl.style(boldCenteredStyle).setFontSize(12);
    bold18CenteredStyle = stl.style(boldCenteredStyle).setFontSize(18);
    bold22CenteredStyle = stl.style(boldCenteredStyle).setFontSize(22);
    columnStyle = stl.style(rootStyle).setVerticalAlignment(VerticalAlignment.MIDDLE);
    columnTitleStyle = stl.style(columnStyle).setBorder(stl.pen1Point())
        .setHorizontalAlignment(HorizontalAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY).bold();
    groupStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.LEFT);
    subtotalStyle = stl.style(boldStyle).setTopBorder(stl.pen1Point());

    StyleBuilder crosstabGroupStyle = stl.style(columnTitleStyle);
    StyleBuilder crosstabGroupTotalStyle = stl.style(columnTitleStyle).setBackgroundColor(new Color(170, 170, 170));
    StyleBuilder crosstabGrandTotalStyle = stl.style(columnTitleStyle).setBackgroundColor(new Color(140, 140, 140));
    StyleBuilder crosstabCellStyle = stl.style(columnStyle).setBorder(stl.pen1Point());

    TableOfContentsCustomizerBuilder tableOfContentsCustomizer = tableOfContentsCustomizer().setHeadingStyle(0,
        stl.style(rootStyle).bold());

    reportTemplate = template().setLocale(Locale.ENGLISH).setColumnStyle(columnStyle)
        .setColumnTitleStyle(columnTitleStyle).setGroupStyle(groupStyle).setGroupTitleStyle(groupStyle)
        .setSubtotalStyle(subtotalStyle).highlightDetailEvenRows().crosstabHighlightEvenRows()
        .setCrosstabGroupStyle(crosstabGroupStyle).setCrosstabGroupTotalStyle(crosstabGroupTotalStyle)
        .setCrosstabGrandTotalStyle(crosstabGrandTotalStyle).setCrosstabCellStyle(crosstabCellStyle)
        .setTableOfContentsCustomizer(tableOfContentsCustomizer);

    currencyType = new CurrencyType();

    image = cmp.image(Templates.class.getClassLoader().getResource("tobb_logo.png")).setFixedDimension(60, 60);

    dynamicReportsComponent = cmp.horizontalList(image, cmp.verticalList(
        cmp.text("").setStyle(bold22CenteredStyle).setHorizontalAlignment(HorizontalAlignment.LEFT)));

    footerComponent = cmp.pageXofY().setStyle(stl.style(boldCenteredStyle).setTopBorder(stl.pen1Point()));

    lastRow = stl.conditionalStyle(new LastRowCondition()).setBottomBorder(stl.penDouble());

    border = stl.border().setBottomPen(stl.penDashed().setLineWidth(0.3f))
        .setLeftPen(stl.penDouble().setLineColor(Color.BLACK)).setTopPen(stl.penDashed().setLineWidth(0.3f))
        .setRightPen(stl.penDouble().setLineColor(Color.BLACK));

    htmlMarkupStyle = DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.LEFT)
        .conditionalStyles(lastRow).setVerticalAlignment(VerticalAlignment.MIDDLE).setMarkup(Markup.HTML)
        .setBorder(Templates.border).setPadding(5);

    generalBorderStyle = stl.style().conditionalStyles(lastRow).setBorder(Templates.border).setPadding(5)
        .setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.LEFT);

    wl = Whitelist.simpleText().addTags("a", "abbr", "acronym", "address", "area", "b", "bdo", "big", "blockquote",
        "br", "button", "caption", "center", "cite", "code", "col", "colgroup", "dd", "del", "dfn", "dir",
        "div", "dl", "dt", "em", "fieldset", "font", "form", "h1", "h2", "h3", "h4", "h5", "h6", "hr", "i",
        "img", "input", "ins", "kbd", "label", "legend", "li", "map", "menu", "ol", "optgroup", "option", "p",
        "pre", "q", "s", "samp", "select", "small", "span", "strike", "strong", "sub", "sup", "table", "tbody",
        "td", "textarea", "tfoot", "th", "thead", "u", "tr", "tt", "u", "ul", "var");
  }

  /**
   * Creates custom component which is possible to add to any report band
   * component
   */
  public static ComponentBuilder<?, ?> createTitleComponent(String label) {
    return cmp.horizontalList()
        .add(cmp.horizontalList(image,
            cmp.verticalList(cmp.text(label).setStyle(bold22CenteredStyle)
                .setHorizontalAlignment(HorizontalAlignment.LEFT))))
        .newRow()
        .add(cmp.text(DateFormatUtils.format(new Date(), "dd.MM.yyyy")).setStyle(italicStyle)
            .setHorizontalAlignment(HorizontalAlignment.RIGHT))
        .newRow().add(cmp.line()).newRow().add(cmp.verticalGap(10));
  }

  /**
   * Creates custom component which is possible to add to any report band
   * component
   */
  public static ComponentBuilder<?, ?> createSubtitleComponent(String label) {
    return cmp.horizontalList()
        .add(cmp.horizontalList(image,
            cmp.verticalList(
                cmp.text(label).setStyle(boldStyle).setHorizontalAlignment(HorizontalAlignment.LEFT))))
        .newRow().add(cmp.line());
  }

  public static ComponentBuilder<?, ?> createTitleWithSubComponent(String label, String sub) {

    return cmp.horizontalList()
        .add(cmp.horizontalList(image,
            cmp.verticalList(cmp.text(label).setStyle(bold22CenteredStyle)
                .setHorizontalAlignment(HorizontalAlignment.LEFT))))
        .newRow()
        .add(cmp.verticalList().add(cmp.text(sub).setStyle(Templates.bold12CenteredStyle),
            cmp.text(DateFormatUtils.format(new Date(), "dd.MM.yyyy")).setStyle(italicStyle)
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)))
        .newRow().add(cmp.line()).newRow().add(cmp.verticalGap(10));
  }

  public static CurrencyValueFormatter createCurrencyValueFormatter(String label) {
    return new CurrencyValueFormatter(label);
  }

  public static class CurrencyType extends BigDecimalType {
    private static final long serialVersionUID = 1L;

    @Override
    public String getPattern() {
      return "#,###.00 TL";
    }
  }

  private static class CurrencyValueFormatter extends AbstractValueFormatter<String, Number> {
    private static final long serialVersionUID = 1L;

    private String label;

    public CurrencyValueFormatter(String label) {
      this.label = label;
    }

    @Override
    public String format(Number value, ReportParameters reportParameters) {
      return label + currencyType.valueToString(value, reportParameters.getLocale());
    }
  }

  private static class LastRowCondition extends AbstractComplexExpression<Boolean> {
    @Override
    public Boolean evaluate(List<?> list, ReportParameters reportParameters) {

      int size = (((JRBeanCollectionDataSource) reportParameters.getParameterValue("REPORT_DATA_SOURCE"))
          .getData().size());
      return reportParameters.getReportRowNumber() == (size);

    }
  }
}
