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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getQuantityOfProductInPharmacy.sql
 * Ficheiro:  getQuantityOfProductInPharmacy.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getQuantityOfProductInPharmacy.sql
 */

create or replace function getQuantityOfProductInPharmacy(p_PharmacyId Product.Pharmacyid%TYPE, p_barCode Product.barCode%TYPE) return integer
  is
    v_number integer;
BEGIN
     select stock into v_number
	from PRODUCT
	where Pharmacyid = p_PharmacyId AND p_barCode = barcode;
    return v_number;

END;
/
commit;