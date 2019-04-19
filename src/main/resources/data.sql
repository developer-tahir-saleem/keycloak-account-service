--
-- Dumping data for table `permissaion`
--

INSERT INTO `permission` (`id`, `parent_id`, `name`, `description`, `deleted`) VALUES
-- ======================================== User Module =============================xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx===========
-- --------------------------------------- Child Permission -----------------------------------
  (unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000100', '-', '')), '', 'USER', 'USER MODULE', 0),
 --------------------------------------- Child Permission -----------------------------------
  (unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000101', '-', '')), unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000100', '-', ''))
    , 'CREATE', 'USER MODULE CREATE', 0),
  (unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000102', '-', '')), unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000100', '-', ''))
    , 'UPDATE', 'USER MODULE UPDATE', 0),
  (unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000103', '-', '')), unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000100', '-', ''))
    , 'DELETE', 'USER MODULE DELETE', 0),
  (unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000104', '-', '')), unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000100', '-', ''))
    , 'LIST', 'USER MODULE LIST', 0),
  (unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000105', '-', '')), unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000100', '-', ''))
    , 'LOGIN', 'USER MODULE LOGIN', 0),
  (unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000106', '-', '')), unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000100', '-', ''))
    , 'REGISTER', 'USER MODULE REGISTER', 0),
  (unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000107', '-', '')), unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000100', '-', ''))
    , 'RESET_PASSWORD', 'USER MODULE RESET_PASSWORD', 0),
  (unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000108', '-', '')), unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000100', '-', ''))
    , 'RESET_PASSWORD', 'USER MODULE RESET_PASSWORD', 0),
  (unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000109', '-', '')), unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000100', '-', ''))
    , 'UPDATE_STATUS', 'USER MODULE UPDATE_STATUS', 0),
  (unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000110', '-', '')), unhex(replace('a21f6182-0d26-11e9-ab14-d663bd000100', '-', ''))
    , 'UNLOCK_SCREEN', 'USER MODULE UNLOCK_SCREEN', 0);
-- ======================================== User Module ========================================


--
-- Dumping data for table `user`
--

--INSERT INTO `user` (`id`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`, `password`, `type`, `username`, `email`, `deleted`) VALUES
--(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), b'1', b'1', b'1', b'1', '$2a$10$ZI750U4ZXuu6svpX8.KEpOncNC7LPQ.wP3x3JdtYTXdeb4khozwXm', 'OWNER', 'admin@gmail.com', 'admin@gmail.com', false);

-- --------------------------------------------------------


--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`, `permission`, `deleted`) VALUES
(unhex(replace('a21f6182-0d26-11e9-ab14-d663bd873d93','-','')),'USER', 'USER', false),
(unhex(replace('a21f642a-0d26-11e9-ab14-d663bd873d93','-','')),'USER', 'ADMIN', false),
(unhex(replace('a21f657e-0d26-11e9-ab14-d663bd873d93','-','')),'USER', 'MANAGER', false),
(unhex(replace('a21f68da-0d26-11e9-ab14-d663bd873d93','-','')),'USER', 'SUPPERVISOR', false),
(unhex(replace('a21f6a7e-0d26-11e9-ab14-d663bd873d93','-','')),'USER', 'CLIENT', false),
(unhex(replace('a21f6bb4-0d26-11e9-ab14-d663bd873d93','-','')),'USER', 'WORKER', false),
(unhex(replace('a21f6f38-0d26-11e9-ab14-d663bd873d93','-','')),'USER', 'ANDROID', false),
(unhex(replace('a21f708c-0d26-11e9-ab14-d663bd873d93','-','')),'USER', 'IMAWEB', false),
(unhex(replace('25c1eac8-0d27-11e9-ab14-d663bd873d93','-','')),'USER', 'IMADMIN', false),
(unhex(replace('25c1f036-0d27-11e9-ab14-d663bd873d93','-','')),'USER', 'IOS', false);



-- --------------------------------------------------------

--
-- Dumping data for table `user_roles`
--

--INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
--(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f6182-0d26-11e9-ab14-d663bd873d93','-',''))),
--(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f642a-0d26-11e9-ab14-d663bd873d93','-',''))),
--(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f657e-0d26-11e9-ab14-d663bd873d93','-',''))),
--(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f68da-0d26-11e9-ab14-d663bd873d93','-',''))),
--(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f6a7e-0d26-11e9-ab14-d663bd873d93','-',''))),
--(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f6bb4-0d26-11e9-ab14-d663bd873d93','-',''))),
--(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f6f38-0d26-11e9-ab14-d663bd873d93','-',''))),
--(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f708c-0d26-11e9-ab14-d663bd873d93','-',''))),
--(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('25c1eac8-0d27-11e9-ab14-d663bd873d93','-',''))),
--(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('25c1f036-0d27-11e9-ab14-d663bd873d93','-','')));
--
--


--
-- Dumping data for table `user`
--

INSERT INTO `app_config` (`id`, `app_key`, `app_value`, `description`, `deleted`) VALUES
(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), 'app_version', '1.0.0','The application is on current version !',false);

-- --------------------------------------------------------
