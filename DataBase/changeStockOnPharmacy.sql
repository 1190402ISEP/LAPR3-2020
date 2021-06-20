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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/changeStockOnPharmacy.sql
 * Ficheiro:  changeStockOnPharmacy.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: changeStockOnPharmacy.sql
 */

create or replace procedure changeStockOnPharmacy(p_stock Product.stock%TYPE, p_Barcode Product.barcode%TYPE, p_pharmacyId Pharmacy.id%TYPE)
    IS
    v_CurrentStock Product.stock%TYPE;
BEGIN

    select stock into v_CurrentStock
    from Product
    where Pharmacyid = p_pharmacyId AND barCode = p_Barcode;

    UPDATE Product SET stock=v_CurrentStock-p_stock WHERE Pharmacyid = p_pharmacyId AND barCode = p_Barcode;

END;
/