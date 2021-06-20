create or replace procedure registerOrderProduct(p_order_id orders.id%type, p_barcode product.barcode%type,
                                                 p_quantity productperorder.quantity%type,
                                                 p_pharmacyID integer, p_stockNeeded out integer)
    is

    cursor c_invoice is (select TOTALPRICE, DELIVERYFEE, TOTALWITHDISCOUNT, PRODUCTSTOTALPRICE
                         from INVOICE
                         where ORDERID = p_order_id);
    c_totalPrice         invoice.totalprice%type;
    c_deliveryFee        invoice.deliveryfee%type;
    c_totalWithDiscount  invoice.totalwithdiscount%type;
    c_productsTotalPrice invoice.PRODUCTSTOTALPRICE%type;
    v_iva                product.iva%type;
    v_preco              product.price%type;
    v_peso               product.weight%type;
    v_pesoAtual          product.weight%type;
    v_stock              int;




begin

    v_stock := getQuantityOfProductInPharmacy(p_pharmacyID, p_barcode);

    if v_stock < p_quantity then
        p_stockNeeded := p_quantity - v_stock;
        update PRODUCT set STOCK = 0 where BARCODE = p_barcode and PHARMACYID = p_pharmacyID;
        update orders set ORDERSTATE = 5 where ID = p_order_id;


    else

        p_stockNeeded := 0;
        update PRODUCT set STOCK = v_stock - p_quantity where BARCODE = p_barcode and PHARMACYID = p_pharmacyID;
    end if;

    select distinct iva into v_iva from PRODUCT where BARCODE = p_barcode;
    select distinct price*p_quantity into v_preco from PRODUCT where BARCODE = p_barcode;
    select distinct WEIGHT into v_peso from PRODUCT where BARCODE = p_barcode;

    select TOTALWEIGHT into v_pesoAtual from ORDERS where id = p_order_id;

    update ORDERS set TOTALWEIGHT = v_pesoAtual + (v_peso * p_quantity) where ID = p_order_id;

    insert into PRODUCTPERORDER(barcode, orderid, quantity, iva) values (p_barcode, p_order_id, p_quantity, v_iva);

    open c_invoice;
    loop
        fetch c_invoice into c_totalPrice, c_deliveryFee, c_totalWithDiscount, c_productsTotalPrice;
        exit when c_invoice%notfound;

        update INVOICE
        set TOTALPRICE         = c_totalPrice + v_preco + (v_preco * (v_iva / 100)),
            PRODUCTSTOTALPRICE = c_productsTotalPrice + v_preco + (v_preco * (v_iva / 100)),
            TOTALWITHDISCOUNT  = c_totalWithDiscount + v_preco + (v_preco * (v_iva / 100))
        where ORDERID = p_order_id;

    end loop;


    /*
 * António Daniel Barbosa Fernandes, [17.05.21 19:26]
 * Copyright (c) 2021.
 *
 * Programador: António Daniel Barbosa Fernandes
 *
 * UserName: anton
 * ----------------
 * INFORMAÇÕES:
 * Nome do Projeto: GITHUB 1190402
 * Módulo: GITHUB 1190402
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/registerOrderProduct.sql
 * Ficheiro:  registerOrderProduct.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: registerOrderProduct.sql
 */


    --atualizar o preço total e o peso total da order
    --atualizar o stock do produto. caso nao haje stock retornar alguma coisa que o identifique para depois no java ser tratado
exception

    when no_data_found then
        raise_application_error(-2056, 'Error!');

end;
/
commit;