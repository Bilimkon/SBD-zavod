package sample.components.sell.productTableView;

import javafx.collections.ObservableList;
import sample.components.sell.Core.Product;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MaxsulotTable extends AbstractTableModel {

    public static final int OBJECT_COL = -1;
    private static final int BARCODE_COL = 0;
    private static final int NAME_COL = 1;
    private static final int TYPE_COL = 2;
    private static final int QUANTITY_COL = 3;
    private static final int COST_COL = 4;
    private static final int DATE_COL = 5;
    private static final int EXPIRE_DATE_COL = 6;
    private static final int SUPLIER_COL = 7;
    private static final int TURLARI_COL = 8;


    private List<Product> employees;

    public MaxsulotTable(ObservableList<Product> theEmployees) {
        employees = theEmployees;
    }


    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return 0;
    }


    @Override
    public Object getValueAt(int row, int col) {

        Product tempEmployee = employees.get(row);

        switch (col) {
            case BARCODE_COL:
                return tempEmployee.getBarcode();
            case NAME_COL:
                return tempEmployee.getName();
            case TYPE_COL:
                return tempEmployee.getType();
            case QUANTITY_COL:
                return tempEmployee.getQuantity();
            case COST_COL :
                return  tempEmployee.getCost();
            case DATE_COL:
                return tempEmployee.getDate_c();
            case EXPIRE_DATE_COL:
                return tempEmployee.getDate_o();
            case OBJECT_COL:
                return tempEmployee;
            default:
                return tempEmployee.getName();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}


