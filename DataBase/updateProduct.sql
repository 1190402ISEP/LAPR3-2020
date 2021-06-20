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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/updateProduct.sql
 * Ficheiro:  updateProduct.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: updateProduct.sql
 */

create or replace procedure updateProduct(pId PRODUCT.ID%TYPE,
                                      pName PRODUCT.NAME%TYPE,
                                      pStock PRODUCT.STOCK%TYPE,
                                      pPrice PRODUCT.PRICE%TYPE,
                                      pWeight PRODUCT.WEIGHT%TYPE,
                                      pIva PRODUCT.IVA%TYPE,
                                      pBarCode PRODUCT.BARCODE%TYPE,
                                      p_pharmacyID pharmacy.id%type )
    IS

BEGIN
    if pName is not null then
        UPDATE PRODUCT SET NAME=pName WHERE ID = pId and p_pharmacyID = PHARMACYID;
    end if;
    
    if pStock is not null then
        UPDATE PRODUCT SET STOCK=pStock WHERE ID = pId and p_pharmacyID = PHARMACYID;
    end if;
    
    if pPrice is not null then
        UPDATE PRODUCT SET PRICE=pPrice WHERE ID = pId and p_pharmacyID = PHARMACYID;
    end if;
    --
    if pWeight is not null then
        UPDATE PRODUCT SET WEIGHT=pWeight WHERE ID = pId and p_pharmacyID = PHARMACYID;
    end if;
    
    if pIva is not null then
        UPDATE PRODUCT SET IVA=pIva WHERE ID = pId and p_pharmacyID = PHARMACYID;
    end if;

    if pBarCode is not null then
        UPDATE PRODUCT SET BARCODE=pBarCode WHERE ID = pId and p_pharmacyID = PHARMACYID;
    end if;
    
END;
/
COMMIT;
