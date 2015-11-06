-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.16


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema barsystems
--

CREATE DATABASE IF NOT EXISTS barsystems;
USE barsystems;

--
-- Definition of table `caixa`
--

DROP TABLE IF EXISTS `caixa`;
CREATE TABLE `caixa` (
  `id_caixa` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_centro_custo` int(11) NOT NULL DEFAULT '0',
  `data_abertura` datetime NOT NULL,
  `data_fechamento` datetime DEFAULT NULL,
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_caixa`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `caixa`
--

/*!40000 ALTER TABLE `caixa` DISABLE KEYS */;
INSERT INTO `caixa` (`id_caixa`,`id_centro_custo`,`data_abertura`,`data_fechamento`,`excluido`) VALUES 
 (28,1,'2015-05-01 23:34:31','2015-05-20 00:27:11',0),
 (29,1,'2015-05-02 00:01:56','2015-05-20 00:26:30',0),
 (30,1,'2015-05-05 18:45:04','2015-05-20 00:24:37',0),
 (31,1,'2015-05-20 00:24:47','2015-05-20 00:25:07',0),
 (32,1,'2015-05-20 00:27:20','2015-05-20 00:27:35',0),
 (33,1,'2015-05-20 00:38:42','2015-05-20 00:38:57',0),
 (34,1,'2015-05-20 00:38:59','2015-05-20 00:39:53',0),
 (35,1,'2015-05-20 00:39:54','2015-05-20 00:39:57',0),
 (36,1,'2015-05-20 00:39:57','2015-05-21 20:45:24',0),
 (37,1,'2015-05-21 20:45:26','2015-05-21 20:45:34',0),
 (38,1,'2015-05-22 00:25:28','2015-05-26 10:35:50',0),
 (39,1,'2015-05-26 10:35:52','2015-05-26 10:36:07',0),
 (40,1,'2015-05-26 10:37:12','2015-05-26 10:37:48',0),
 (41,1,'2015-05-26 10:37:48','2015-05-26 10:40:06',0),
 (42,1,'2015-05-26 10:40:12','2015-05-26 11:06:17',0),
 (43,1,'2015-05-26 11:06:28','2015-05-26 11:07:37',0),
 (44,1,'2015-05-26 11:07:46',NULL,0),
 (45,1,'2015-06-08 10:18:08','2015-06-17 18:30:31',0),
 (46,1,'2015-06-17 18:30:34',NULL,0);
/*!40000 ALTER TABLE `caixa` ENABLE KEYS */;


--
-- Definition of table `caixa_movimentacao`
--

DROP TABLE IF EXISTS `caixa_movimentacao`;
CREATE TABLE `caixa_movimentacao` (
  `id_caixa_movimentacao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_caixa_fk` int(10) unsigned NOT NULL,
  `descricao` text NOT NULL,
  `id_forma_pagamento_fk` tinyint(3) unsigned NOT NULL,
  `valor` double NOT NULL,
  `tipo` varchar(13) NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `id_usuario` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_caixa_movimentacao`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `caixa_movimentacao`
--

/*!40000 ALTER TABLE `caixa_movimentacao` DISABLE KEYS */;
INSERT INTO `caixa_movimentacao` (`id_caixa_movimentacao`,`id_caixa_fk`,`descricao`,`id_forma_pagamento_fk`,`valor`,`tipo`,`data`,`excluido`,`id_usuario`) VALUES 
 (65,0,'Abertura do caixa',1,100,'Abertura','2015-05-01 23:34:33',0,2),
 (66,0,'Compra teste',1,0,'Despesa','2015-05-01 23:34:33',0,2),
 (67,0,'Compra teste',1,2,'Despesa','2015-05-01 23:34:33',0,2),
 (68,0,'Compra teste',1,2,'Despesa','2015-05-01 23:34:33',0,2),
 (69,28,'compras!',1,2,'Despesa','2015-05-01 23:43:22',0,2),
 (70,28,'compras!',1,3.5,'Despesa','2015-05-01 23:43:22',0,2),
 (71,29,'Abertura do caixa',1,35,'Abertura','2015-05-02 00:01:58',0,2),
 (72,29,'Pagto da parcela 1 da compra asdasd',1,1.8,'Despesa','2015-05-02 00:01:58',0,2),
 (73,29,'Pagto da parcela 2 da compra asdasd',1,1.8,'Despesa','2015-05-02 00:01:58',0,2),
 (74,29,'Pagto da parcela 3 da compra asdasd',1,1.8,'Despesa','2015-05-02 00:01:59',0,2),
 (75,29,'Sangria',1,200,'Sangria','2015-05-03 21:12:24',0,2),
 (76,29,'Saldo vindo do centro de custo Conta bancária',1,100,'Transferência','2015-05-03 21:31:14',0,2),
 (77,30,'Abertura do caixa',1,100,'Abertura','2015-05-05 18:45:06',0,2),
 (78,30,'Pagto da parcela 1 da compra dsadsad',1,15,'Despesa','2015-05-05 18:45:06',0,2),
 (79,30,'Pagto da parcela 2 da compra dsadsad',1,10.5,'Despesa','2015-05-05 18:45:06',0,2),
 (80,30,'teste',1,100,'Receita','2015-05-13 21:46:30',0,2),
 (81,30,'CAIXAAAAAA',1,400,'Receita','2015-05-13 21:56:46',0,2),
 (82,30,'sadasdsa',1,12,'Receita','2015-05-13 21:57:58',0,2),
 (83,30,'Sangria',1,100,'Transferência','2015-05-20 00:24:00',0,2),
 (84,31,'Abertura do caixa',1,100,'Abertura','2015-05-20 00:24:48',0,2),
 (85,32,'Abertura do caixa',1,100,'Abertura','2015-05-20 00:27:21',0,2),
 (86,33,'Abertura do caixa',1,960,'Abertura','2015-05-20 00:38:43',0,2),
 (87,34,'Abertura do caixa',1,1920,'Abertura','2015-05-20 00:38:59',0,2),
 (88,35,'Abertura do caixa',1,3840,'Abertura','2015-05-20 00:39:54',0,2),
 (89,36,'Abertura do caixa',1,3840,'Abertura','2015-05-20 00:39:58',0,2),
 (90,36,'Saldo vindo do centro de custo Conta bancária',1,200,'Transferência','2015-05-21 20:02:44',0,2),
 (91,36,'Sangria',1,300,'Transferência','2015-05-21 20:04:54',0,2),
 (92,36,'Saldo vindo do centro de custo Conta',1,100,'Transferência','2015-05-21 20:05:26',0,2),
 (93,36,'Sangria',1,100,'Transferência','2015-05-21 20:10:49',0,2),
 (94,36,'Sangria',1,200,'Transferência','2015-05-21 20:11:30',0,2),
 (95,37,'Abertura do caixa',1,3540,'Abertura','2015-05-21 20:45:26',0,2),
 (96,37,'Sangria',1,3540,'Transferência','2015-05-21 20:45:32',0,2),
 (97,38,'Abertura do caixa',1,0,'Abertura','2015-05-22 00:25:29',0,2),
 (98,38,'Saldo vindo do centro de custo Conta',1,200,'Transferência','2015-05-22 00:25:46',0,2),
 (99,39,'Abertura do caixa',1,200,'Abertura','2015-05-26 10:35:54',0,2),
 (100,39,'Sangria',1,200,'Transferência','2015-05-26 10:36:05',0,2),
 (101,40,'Abertura do caixa',1,1000,'Abertura','2015-05-26 10:37:14',0,2),
 (102,40,'Saldo vindo do centro de custo Conta',1,1000,'Transferência','2015-05-26 10:37:14',0,2),
 (103,41,'Abertura do caixa',1,1000,'Abertura','2015-05-26 10:37:49',0,2),
 (104,41,'Sangria',1,1000,'Transferência','2015-05-26 10:39:17',0,2),
 (105,42,'Abertura do caixa',1,0,'Abertura','2015-05-26 10:40:12',0,2),
 (106,42,'Saldo vindo do centro de custo Conta',1,2000,'Transferência','2015-05-26 10:40:13',0,2),
 (107,42,'Sangria. Centro de custo de destino: Conta',1,2000,'Transferência','2015-05-26 11:06:15',0,2),
 (108,43,'Abertura do caixa',1,0,'Abertura','2015-05-26 11:06:29',0,2),
 (109,43,'Saldo vindo do centro de custo Conta',1,1300,'Transferência','2015-05-26 11:06:29',0,2),
 (110,43,'Sangria. Centro de custo de destino: Conta',1,1300,'Transferência','2015-05-26 11:07:33',0,2),
 (111,44,'Abertura do caixa',1,0,'Abertura','2015-05-26 11:07:46',0,2),
 (112,44,'Saldo vindo do centro de custo Conta',1,1000,'Transferência','2015-05-26 11:07:47',0,2),
 (113,44,'Saldo vindo do centro de custo Conta',1,200,'Transferência','2015-06-02 08:48:06',0,2),
 (114,45,'Abertura do caixa',1,100,'Abertura','2015-06-08 10:18:08',0,2),
 (115,45,'Pagto da parcela 1 da compra teste',1,70,'Despesa','2015-06-08 10:18:08',0,2),
 (116,45,'Pagto da parcela 2 da compra teste',1,56,'Despesa','2015-06-08 10:18:09',0,2),
 (117,45,'Pagto da parcela 3 da compra teste',1,56,'Despesa','2015-06-08 10:18:09',0,2),
 (118,45,'Pagto da parcela 1 da compra 123',1,5.8,'Despesa','2015-06-15 02:10:12',0,2),
 (119,45,'Pagto da parcela 2 da compra 123',1,5.8,'Despesa','2015-06-15 02:10:12',0,2),
 (120,45,'Pagto da parcela 3 da compra 123',1,5.8,'Despesa','2015-06-15 02:10:12',0,2),
 (121,45,'Pagto da parcela 1 da compra sadasdasd',1,96,'Despesa','2015-06-15 02:12:36',0,2),
 (122,45,'Pagto da parcela 1 da compra aeooooo',1,5,'Despesa','2015-06-15 02:19:39',0,2),
 (123,45,'Pagto da parcela 2 da compra aeooooo',1,7.5,'Despesa','2015-06-15 02:19:39',0,2),
 (124,45,'Pagto da parcela 3 da compra aeooooo',1,7.5,'Despesa','2015-06-15 02:19:39',0,2),
 (125,46,'Abertura do caixa',1,1200,'Abertura','2015-06-17 18:30:34',0,2),
 (126,46,'Pagto da parcela 1 da compra compra x',1,50,'Despesa','2015-06-17 19:01:23',0,2),
 (127,46,'Pagto da parcela 2 da compra compra x',1,49.75,'Despesa','2015-06-17 19:01:23',0,2),
 (128,46,'a',1,500,'Despesa','2015-07-01 09:42:57',1,2);
/*!40000 ALTER TABLE `caixa_movimentacao` ENABLE KEYS */;


--
-- Definition of table `centro_custo`
--

DROP TABLE IF EXISTS `centro_custo`;
CREATE TABLE `centro_custo` (
  `id_centro_custo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `tipo` varchar(15) NOT NULL DEFAULT 'Caixa',
  `saldo` double NOT NULL DEFAULT '0',
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_centro_custo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `centro_custo`
--

/*!40000 ALTER TABLE `centro_custo` DISABLE KEYS */;
INSERT INTO `centro_custo` (`id_centro_custo`,`nome`,`tipo`,`saldo`,`excluido`) VALUES 
 (1,'Caixa geral','Caixa',400,0),
 (7,'Conta bancária','Conta bancária',0,1),
 (8,'Conta','Conta bancária',2300,0),
 (9,'Conta do Bradesco','Conta bancária',300,0),
 (10,'Caixa do marcos','Caixa',1040,0),
 (11,'Conta Banco do Brasil','Conta bancária',0,1),
 (12,'Caixa 3','Caixa',0,0);
/*!40000 ALTER TABLE `centro_custo` ENABLE KEYS */;


--
-- Definition of table `centro_custo_responsavel`
--

DROP TABLE IF EXISTS `centro_custo_responsavel`;
CREATE TABLE `centro_custo_responsavel` (
  `id_centro_custo_fk` int(10) unsigned NOT NULL,
  `id_usuario_fk` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_centro_custo_fk`,`id_usuario_fk`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `centro_custo_responsavel`
--

/*!40000 ALTER TABLE `centro_custo_responsavel` DISABLE KEYS */;
INSERT INTO `centro_custo_responsavel` (`id_centro_custo_fk`,`id_usuario_fk`) VALUES 
 (1,1),
 (1,2),
 (1,4),
 (8,1),
 (12,1),
 (12,4);
/*!40000 ALTER TABLE `centro_custo_responsavel` ENABLE KEYS */;


--
-- Definition of table `centro_estoque`
--

DROP TABLE IF EXISTS `centro_estoque`;
CREATE TABLE `centro_estoque` (
  `id_centro_estoque` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL DEFAULT '',
  `ativo` tinyint(1) DEFAULT '1',
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_centro_estoque`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `centro_estoque`
--

/*!40000 ALTER TABLE `centro_estoque` DISABLE KEYS */;
INSERT INTO `centro_estoque` (`id_centro_estoque`,`nome`,`ativo`,`excluido`) VALUES 
 (1,'Geladeira 1',1,0),
 (2,'Geladeira 2',1,0),
 (3,'Porao',1,0),
 (4,'abc',1,1),
 (5,'Porao 2',1,0),
 (6,'Porao 32',0,0),
 (7,'teste',1,1),
 (8,'Teste!!! 123',0,1);
/*!40000 ALTER TABLE `centro_estoque` ENABLE KEYS */;


--
-- Definition of table `centro_estoque_produto`
--

DROP TABLE IF EXISTS `centro_estoque_produto`;
CREATE TABLE `centro_estoque_produto` (
  `id_produto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_centro_estoque` int(10) unsigned NOT NULL DEFAULT '0',
  `quantidade` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_produto`,`id_centro_estoque`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `centro_estoque_produto`
--

/*!40000 ALTER TABLE `centro_estoque_produto` DISABLE KEYS */;
INSERT INTO `centro_estoque_produto` (`id_produto`,`id_centro_estoque`,`quantidade`) VALUES 
 (1,1,22),
 (2,1,1),
 (2,3,3),
 (2,4,2),
 (3,1,44),
 (4,1,3);
/*!40000 ALTER TABLE `centro_estoque_produto` ENABLE KEYS */;


--
-- Definition of table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id_cliente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `data_nascimento` varchar(10) DEFAULT NULL,
  `rg` varchar(15) DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `celular` varchar(15) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `id_pais_fk` varchar(30) DEFAULT NULL,
  `bloqueado` tinyint(1) DEFAULT '0',
  `observacao` text,
  `excluido` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cliente`
--

/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id_cliente`,`nome`,`data_nascimento`,`rg`,`cpf`,`telefone`,`celular`,`email`,`cep`,`endereco`,`bairro`,`cidade`,`estado`,`id_pais_fk`,`bloqueado`,`observacao`,`excluido`) VALUES 
 (1,'marcos',NULL,NULL,'441.729.778-98','19 36841615',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'26',0,NULL,0),
 (2,'filipe',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'26',0,NULL,0),
 (3,'luan',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'26',0,NULL,0),
 (4,'Teste 1','12/31/2321','123123213','123.123.123-21','(12) 3123-1231','(12) 312312312','123','99999999','123','123','123456','SP','26',1,'teste aeeeee',1),
 (5,'teste','  /  /    ','','123.123.123-22','(  )     -    ','(  )          ','','        ','','','','  ','26',0,'',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


--
-- Definition of table `compra`
--

DROP TABLE IF EXISTS `compra`;
CREATE TABLE `compra` (
  `id_compra` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) NOT NULL,
  `id_fornecedor_fk` int(10) unsigned NOT NULL,
  `codigo_nota` text,
  `data` date NOT NULL,
  `id_funcionario_fk` int(10) unsigned DEFAULT NULL,
  `acrescimo` float NOT NULL,
  `desconto` float DEFAULT '0',
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `id_forma_pagamento_fk` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_compra`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `compra`
--

/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;


--
-- Definition of table `compra_pagamento`
--

DROP TABLE IF EXISTS `compra_pagamento`;
CREATE TABLE `compra_pagamento` (
  `id_compra_pagamento` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_compra` int(10) unsigned NOT NULL DEFAULT '0',
  `numero_parcela` int(10) unsigned NOT NULL DEFAULT '0',
  `valor` float NOT NULL DEFAULT '0',
  `excluido` tinyint(1) NOT NULL DEFAULT '0',
  `data_pagamento` date DEFAULT '0000-00-00',
  `data_vencimento` date NOT NULL DEFAULT '0000-00-00',
  `liquidado` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_compra_pagamento`) USING BTREE,
  KEY `FK_pagamentos_compra_1` (`id_compra`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `compra_pagamento`
--

/*!40000 ALTER TABLE `compra_pagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra_pagamento` ENABLE KEYS */;


--
-- Definition of table `compra_produto`
--

DROP TABLE IF EXISTS `compra_produto`;
CREATE TABLE `compra_produto` (
  `id_produto_compra` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_produto_fk` int(10) unsigned NOT NULL DEFAULT '0',
  `valor` float NOT NULL DEFAULT '0',
  `quantidade` int(10) unsigned NOT NULL DEFAULT '0',
  `id_centro_estoque_fk` int(10) unsigned NOT NULL DEFAULT '0',
  `id_compra_fk` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_produto_compra`) USING BTREE,
  KEY `id_produtos` (`id_produto_fk`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1 PACK_KEYS=1;

--
-- Dumping data for table `compra_produto`
--

/*!40000 ALTER TABLE `compra_produto` DISABLE KEYS */;
INSERT INTO `compra_produto` (`id_produto_compra`,`id_produto_fk`,`valor`,`quantidade`,`id_centro_estoque_fk`,`id_compra_fk`) VALUES 
 (21,1,9,3,1,23),
 (22,1,9,3,1,24),
 (23,1,9,3,1,25),
 (24,1,24,4,1,26),
 (25,2,12,3,1,26),
 (26,1,15,3,1,27),
 (27,2,15,3,3,27),
 (28,1,9,3,1,28),
 (29,1,12,3,1,29),
 (30,3,200,5,1,30),
 (31,3,6,2,1,31),
 (32,3,64,32,1,32),
 (33,3,12,3,1,33),
 (34,4,129,3,1,34),
 (35,3,64,2,1,34);
/*!40000 ALTER TABLE `compra_produto` ENABLE KEYS */;


--
-- Definition of table `conta_bancaria_movimentacao`
--

DROP TABLE IF EXISTS `conta_bancaria_movimentacao`;
CREATE TABLE `conta_bancaria_movimentacao` (
  `id_conta_bancaria_movimentacao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_centro_custo_fk` int(10) unsigned NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `id_forma_pagamento_fk` tinyint(3) unsigned NOT NULL,
  `numero_parcelas` tinyint(3) unsigned NOT NULL,
  `valor` float NOT NULL,
  `tipo` varchar(13) NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `id_usuario` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`id_conta_bancaria_movimentacao`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `conta_bancaria_movimentacao`
--

/*!40000 ALTER TABLE `conta_bancaria_movimentacao` DISABLE KEYS */;
INSERT INTO `conta_bancaria_movimentacao` (`id_conta_bancaria_movimentacao`,`id_centro_custo_fk`,`descricao`,`id_forma_pagamento_fk`,`numero_parcelas`,`valor`,`tipo`,`data`,`excluido`,`id_usuario`) VALUES 
 (1,5,'abc',1,1,100,'Receita','2015-03-21 23:16:59',0,2),
 (2,5,'123',1,1,12,'Despesa','2015-03-21 23:38:59',0,2),
 (3,5,'asdsadsa',1,1,100,'Receita','2015-03-23 16:53:37',0,2),
 (4,5,'sdasdasd',1,1,12,'Despesa','2015-03-23 18:17:19',0,2),
 (5,5,'ddasda',1,1,1,'Receita','2015-03-23 18:35:28',1,2),
 (6,5,'12334',1,1,23,'Receita','2015-03-23 18:46:02',0,2),
 (7,5,'ewq',1,1,1,'Despesa','2015-03-23 18:46:20',0,2),
 (8,5,'sadsad',4,1,1,'Receita','2015-04-09 23:06:15',0,2),
 (9,2,'aeeee',1,1,10,'Despesa','2015-04-09 23:13:01',0,2),
 (10,1,'teste 2',1,1,12,'Receita','2015-04-09 23:35:26',0,2),
 (11,2,'TESTE RECEITA AGENDADA',1,1,100,'Receita','2015-04-09 23:35:50',0,2),
 (12,5,'pagamento',4,1,20,'Despesa','2015-04-11 17:13:51',0,2),
 (13,5,'assASas',1,1,12,'Receita','2015-04-11 17:14:34',0,2),
 (14,7,'Saldo vindo do centro de custo Caixa',1,1,200,'Sangria','2015-05-03 21:12:24',0,2),
 (15,7,'Sangria',1,1,100,'Transferência','2015-05-03 21:31:14',0,2),
 (16,7,'Saldo vindo do centro de custo Caixa',1,1,100,'Transferência','2015-05-20 00:24:01',0,2),
 (17,7,'Sangria',1,1,200,'Transferência','2015-05-21 20:02:44',0,2),
 (18,8,'Saldo vindo do centro de custo Caixa',1,1,300,'Transferência','2015-05-21 20:04:55',0,2),
 (19,8,'Sangria',1,1,100,'Transferência','2015-05-21 20:05:26',0,2),
 (20,8,'Saldo vindo do centro de custo Caixa',1,1,100,'Transferência','2015-05-21 20:10:49',0,2),
 (21,8,'Saldo vindo do centro de custo Caixa',1,1,200,'Transferência','2015-05-21 20:11:30',0,2),
 (22,8,'Saldo vindo do centro de custo Caixa',1,1,3540,'Transferência','2015-05-21 20:45:32',0,2),
 (23,8,'Sangria',1,1,200,'Transferência','2015-05-22 00:25:46',0,2),
 (24,8,'Saldo vindo do centro de custo Caixa',1,1,200,'Transferência','2015-05-26 10:36:05',0,2),
 (25,8,'Sangria',1,1,1000,'Transferência','2015-05-26 10:37:14',0,2),
 (26,8,'Saldo vindo do centro de custo Caixa',1,1,1000,'Transferência','2015-05-26 10:39:17',0,2),
 (27,8,'Sangria',1,1,2000,'Transferência','2015-05-26 10:40:13',0,2),
 (28,8,'Saldo vindo do centro de custo Caixa',1,1,2000,'Transferência','2015-05-26 11:06:15',0,2),
 (29,8,'Sangria. Centro de custo de destino: Caixa',1,1,1300,'Transferência','2015-05-26 11:06:29',0,2),
 (30,8,'Saldo vindo do centro de custo Caixa',1,1,1300,'Transferência','2015-05-26 11:07:33',0,2),
 (31,8,'Sangria. Centro de custo de destino: Caixa',1,1,1000,'Transferência','2015-05-26 11:07:46',0,2),
 (32,8,'Sangria. Centro de custo de destino: Caixa',1,1,200,'Transferência','2015-06-02 08:48:06',0,2),
 (33,8,'Pagto da parcela 1 da compra aaaaaaaa',1,4,3,'Despesa','2015-06-02 09:19:50',0,2),
 (34,8,'Pagto da parcela 2 da compra aaaaaaaa',1,4,3,'Despesa','2015-06-02 09:19:50',0,2),
 (35,9,'Saldo vindo do centro de custo Caixa',1,1,800,'Transferência','2015-06-17 18:30:47',0,2);
/*!40000 ALTER TABLE `conta_bancaria_movimentacao` ENABLE KEYS */;


--
-- Definition of table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
CREATE TABLE `empresa` (
  `id_empresa` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `razao_social` varchar(45) DEFAULT NULL,
  `nome_fantasia` varchar(45) DEFAULT NULL,
  `cnpj` varchar(20) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `id_pais_fk` int(10) unsigned DEFAULT NULL,
  `inscricao_estadual` varchar(45) DEFAULT NULL,
  `observacao` text,
  `excluido` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_empresa`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `empresa`
--

/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` (`id_empresa`,`razao_social`,`nome_fantasia`,`cnpj`,`telefone`,`email`,`cep`,`endereco`,`bairro`,`cidade`,`estado`,`id_pais_fk`,`inscricao_estadual`,`observacao`,`excluido`) VALUES 
 (10,'empresa','empresa','  .   .   /    -  ','(  )     -    ','','        ','','','','  ',26,'','',0);
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;


--
-- Definition of table `financeiro_despesa`
--

DROP TABLE IF EXISTS `financeiro_despesa`;
CREATE TABLE `financeiro_despesa` (
  `id_despesa` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_compra_fk` int(11) DEFAULT NULL,
  `descricao` text NOT NULL,
  `responsavel` varchar(50) DEFAULT NULL,
  `id_fornecedor_fk` int(10) unsigned NOT NULL,
  `qnt_parcelas` smallint(5) unsigned NOT NULL,
  `numero_parcela` smallint(5) unsigned NOT NULL,
  `data_pagamento` datetime DEFAULT NULL,
  `data_vencimento` datetime NOT NULL,
  `id_forma_pagamento_fk` varchar(35) NOT NULL,
  `valor` float NOT NULL,
  `fixo` tinyint(4) DEFAULT NULL,
  `variavel` tinyint(4) DEFAULT NULL,
  `liquidado` tinyint(1) NOT NULL,
  `agendado` tinyint(4) DEFAULT '0',
  `id_setor_fk` int(10) unsigned DEFAULT NULL,
  `id_movimentacao_caixa_fk` tinyint(4) NOT NULL,
  `id_movimentacao_conta_bancaria_fk` tinyint(4) NOT NULL,
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_despesa`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `financeiro_despesa`
--

/*!40000 ALTER TABLE `financeiro_despesa` DISABLE KEYS */;
INSERT INTO `financeiro_despesa` (`id_despesa`,`id_compra_fk`,`descricao`,`responsavel`,`id_fornecedor_fk`,`qnt_parcelas`,`numero_parcela`,`data_pagamento`,`data_vencimento`,`id_forma_pagamento_fk`,`valor`,`fixo`,`variavel`,`liquidado`,`agendado`,`id_setor_fk`,`id_movimentacao_caixa_fk`,`id_movimentacao_conta_bancaria_fk`,`excluido`) VALUES 
 (64,33,'Parcela de número 1 da compra aeooooo','123',4,5,1,'2015-06-15 02:18:46','2015-06-15 00:00:00','1',5,0,1,1,0,2,122,0,0),
 (65,33,'Parcela de número 2 da compra aeooooo','123',4,5,2,'2015-06-15 02:18:46','2015-07-15 00:00:00','1',7.5,0,1,1,0,2,123,0,0),
 (66,33,'Parcela de número 3 da compra aeooooo','123',4,5,3,'2015-06-15 02:18:46','2015-08-15 00:00:00','1',7.5,0,1,1,0,2,124,0,0),
 (67,33,'Parcela de número 4 da compra aeooooo','123',4,5,4,NULL,'2015-09-15 00:00:00','1',7.5,0,1,0,0,2,0,0,0),
 (68,33,'Parcela de número 5 da compra aeooooo','123',4,5,5,NULL,'2015-10-15 00:00:00','1',7.5,0,1,0,0,2,0,0,0),
 (69,34,'Parcela de número 1 da compra compra x','marcos',4,5,1,'2015-06-17 18:53:47','2015-07-10 00:00:00','1',50,0,1,1,0,2,126,0,0),
 (70,34,'Parcela de número 2 da compra compra x','marcos',4,5,2,'2015-06-17 18:53:47','2015-08-10 00:00:00','1',49.75,0,1,1,0,2,127,0,0),
 (71,34,'Parcela de número 3 da compra compra x','marcos',4,5,3,NULL,'2015-09-10 00:00:00','1',49.75,0,1,0,0,2,0,0,0),
 (72,34,'Parcela de número 4 da compra compra x','marcos',4,5,4,NULL,'2015-10-10 00:00:00','1',49.75,0,1,0,0,2,0,0,0),
 (73,34,'Parcela de número 5 da compra compra x','marcos',4,5,5,NULL,'2015-11-10 00:00:00','1',49.75,0,1,0,0,2,0,0,0);
/*!40000 ALTER TABLE `financeiro_despesa` ENABLE KEYS */;


--
-- Definition of table `financeiro_receita`
--

DROP TABLE IF EXISTS `financeiro_receita`;
CREATE TABLE `financeiro_receita` (
  `id_receita` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) NOT NULL,
  `id_cliente_fk` tinyint(4) NOT NULL DEFAULT '0',
  `id_setor_fk` int(10) unsigned NOT NULL,
  `id_forma_pagamento_fk` int(10) unsigned NOT NULL,
  `valor` float NOT NULL,
  `qnt_parcelas` tinyint(3) unsigned NOT NULL,
  `numero_parcela` tinyint(3) unsigned NOT NULL,
  `id_movimentacao_caixa_fk` int(10) unsigned NOT NULL,
  `id_movimentacao_conta_bancaria_fk` tinyint(4) NOT NULL,
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `data_pagamento` datetime DEFAULT NULL,
  `data_vencimento` datetime DEFAULT NULL,
  `liquidado` tinyint(4) NOT NULL DEFAULT '0',
  `agendado` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id_receita`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `financeiro_receita`
--

/*!40000 ALTER TABLE `financeiro_receita` DISABLE KEYS */;
INSERT INTO `financeiro_receita` (`id_receita`,`descricao`,`id_cliente_fk`,`id_setor_fk`,`id_forma_pagamento_fk`,`valor`,`qnt_parcelas`,`numero_parcela`,`id_movimentacao_caixa_fk`,`id_movimentacao_conta_bancaria_fk`,`excluido`,`data_pagamento`,`data_vencimento`,`liquidado`,`agendado`) VALUES 
 (49,'teste',0,3,1,100,1,1,0,0,0,NULL,'2015-05-03 22:43:20',0,1),
 (50,'teste',0,3,1,100,1,1,80,0,0,'2015-05-13 21:46:30','2015-05-13 21:46:30',1,0),
 (51,'CAIXAAAAAA',0,3,1,400,1,1,81,0,0,'2015-05-13 21:56:46','2015-05-13 21:56:17',1,1),
 (52,'sadasdsa',0,3,1,12,1,1,82,0,0,'2015-05-13 21:57:58','2015-05-13 21:57:49',1,1),
 (53,'123',0,3,1,123,1,1,0,0,0,NULL,'2015-05-13 22:11:56',0,1),
 (54,'asdasdas',0,3,1,32,1,1,0,0,0,NULL,'2015-05-13 22:12:22',0,1),
 (55,'asdsad',0,3,1,12,1,1,0,0,0,NULL,'2015-05-13 22:29:43',0,1);
/*!40000 ALTER TABLE `financeiro_receita` ENABLE KEYS */;


--
-- Definition of table `financeiro_setor`
--

DROP TABLE IF EXISTS `financeiro_setor`;
CREATE TABLE `financeiro_setor` (
  `id_setor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `tipo` varchar(7) NOT NULL,
  `padrao` tinyint(4) DEFAULT '0',
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_setor`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `financeiro_setor`
--

/*!40000 ALTER TABLE `financeiro_setor` DISABLE KEYS */;
INSERT INTO `financeiro_setor` (`id_setor`,`nome`,`tipo`,`padrao`,`excluido`) VALUES 
 (2,'Compras','Despesa',1,0),
 (3,'Entrada no caixa','Receita',1,0),
 (4,'Saída no caixa','Despesa',1,0),
 (5,'Entrada na conta bancária','Receita',1,0),
 (6,'Saída na conta bancária','Despesa',1,0),
 (7,'Conta de energia','Despesa',0,1),
 (8,'teste','Receita',0,1),
 (9,'teste','Receita',0,1),
 (10,'Conta de água','Despesa',0,0);
/*!40000 ALTER TABLE `financeiro_setor` ENABLE KEYS */;


--
-- Definition of table `forma_pagamento`
--

DROP TABLE IF EXISTS `forma_pagamento`;
CREATE TABLE `forma_pagamento` (
  `id_forma_pagamento` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `id_tipo_fk` tinyint(3) unsigned NOT NULL,
  `dias_recebimento` int(10) unsigned NOT NULL DEFAULT '0',
  `padrao` tinyint(1) NOT NULL DEFAULT '0',
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_forma_pagamento`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `forma_pagamento`
--

/*!40000 ALTER TABLE `forma_pagamento` DISABLE KEYS */;
INSERT INTO `forma_pagamento` (`id_forma_pagamento`,`nome`,`id_tipo_fk`,`dias_recebimento`,`padrao`,`excluido`) VALUES 
 (1,'Dinheiro',1,0,1,0),
 (2,'Cartão de débito',3,1,0,0),
 (3,'Cartão de crédito - Visa',2,30,1,0),
 (4,'Cartão de crédito - MasterCard',2,30,1,0),
 (5,'Cartão de crédito - AmericanExpress',2,30,1,0),
 (6,'Cartão de crédito - Elo',2,30,1,0),
 (7,'Cartão de crédito - Diners',2,30,1,0),
 (8,'Boleto',4,3,0,0),
 (9,'Cheque',5,30,0,0),
 (10,'Teste 1',2,3,0,1);
/*!40000 ALTER TABLE `forma_pagamento` ENABLE KEYS */;


--
-- Definition of table `forma_pagamento_maquina_cartao`
--

DROP TABLE IF EXISTS `forma_pagamento_maquina_cartao`;
CREATE TABLE `forma_pagamento_maquina_cartao` (
  `id_maquina_cartao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_maquina_cartao`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `forma_pagamento_maquina_cartao`
--

/*!40000 ALTER TABLE `forma_pagamento_maquina_cartao` DISABLE KEYS */;
INSERT INTO `forma_pagamento_maquina_cartao` (`id_maquina_cartao`,`nome`,`excluido`) VALUES 
 (1,'Cielo',1),
 (2,'Teste',1),
 (3,'Cielo',1),
 (4,'Cielo',0),
 (5,'Clielo',1),
 (6,'Teste 2',1),
 (7,'Teste 222',1);
/*!40000 ALTER TABLE `forma_pagamento_maquina_cartao` ENABLE KEYS */;


--
-- Definition of table `forma_pagamento_taxa_cartao`
--

DROP TABLE IF EXISTS `forma_pagamento_taxa_cartao`;
CREATE TABLE `forma_pagamento_taxa_cartao` (
  `id_taxa_cartao` int(11) NOT NULL AUTO_INCREMENT,
  `id_forma_pagamento_fk` int(10) unsigned NOT NULL,
  `id_maquina_cartao_fk` int(11) NOT NULL,
  `taxa` float NOT NULL DEFAULT '0',
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_taxa_cartao`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `forma_pagamento_taxa_cartao`
--

/*!40000 ALTER TABLE `forma_pagamento_taxa_cartao` DISABLE KEYS */;
INSERT INTO `forma_pagamento_taxa_cartao` (`id_taxa_cartao`,`id_forma_pagamento_fk`,`id_maquina_cartao_fk`,`taxa`,`excluido`) VALUES 
 (1,2,2,1,0),
 (2,3,2,0,0),
 (3,4,4,2.2,1),
 (4,5,2,1.3,0),
 (5,6,2,0,0),
 (6,7,2,1.2,0),
 (7,7,4,3,0),
 (8,5,4,1,0);
/*!40000 ALTER TABLE `forma_pagamento_taxa_cartao` ENABLE KEYS */;


--
-- Definition of table `forma_pagamento_tipo`
--

DROP TABLE IF EXISTS `forma_pagamento_tipo`;
CREATE TABLE `forma_pagamento_tipo` (
  `id_tipo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(17) NOT NULL,
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `forma_pagamento_tipo`
--

/*!40000 ALTER TABLE `forma_pagamento_tipo` DISABLE KEYS */;
INSERT INTO `forma_pagamento_tipo` (`id_tipo`,`nome`,`excluido`) VALUES 
 (1,'Dinheiro',0),
 (2,'Cartão de crédito',0),
 (3,'Cartão de débito',0),
 (4,'Boleto',0),
 (5,'Cheque',0),
 (6,'Vale transporte',0),
 (7,'Vale refeição',0);
/*!40000 ALTER TABLE `forma_pagamento_tipo` ENABLE KEYS */;


--
-- Definition of table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
CREATE TABLE `fornecedor` (
  `id_fornecedor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `razao_social` varchar(45) DEFAULT NULL,
  `nome_fantasia` varchar(45) DEFAULT NULL,
  `cnpj` varchar(20) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `id_pais_fk` int(10) unsigned DEFAULT NULL,
  `inscricao_estadual` varchar(45) DEFAULT NULL,
  `observacao` text,
  `excluido` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_fornecedor`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fornecedor`
--

/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` (`id_fornecedor`,`razao_social`,`nome_fantasia`,`cnpj`,`telefone`,`email`,`cep`,`endereco`,`bairro`,`cidade`,`estado`,`id_pais_fk`,`inscricao_estadual`,`observacao`,`excluido`) VALUES 
 (7,'Marcos Mello LTDA','Marcos Mello','  .   .   /    -  ','(  )     -    ','','12312312','asdsadas','sadsadasd','','  ',55,'','sadasdasdasd',0),
 (8,'Aeeee','Aeee','  .   .   /    -  ','(  )     -    ','','        ','','','','  ',26,'','',0),
 (9,'adsadasd','asdasd','  .   .   /    -  ','(  )     -    ','','        ','','','','  ',1,'','',0);
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;


--
-- Definition of table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE `funcionario` (
  `id_funcionario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `data_nascimento` varchar(10) DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `rg` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `celular` varchar(15) DEFAULT NULL,
  `id_funcao_fk` int(10) unsigned DEFAULT NULL,
  `salario` float NOT NULL DEFAULT '0',
  `cep` varchar(10) DEFAULT NULL,
  `endereco` varchar(50) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `id_pais_fk` int(10) unsigned DEFAULT NULL,
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_funcionario`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `funcionario`
--

/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` (`id_funcionario`,`nome`,`data_nascimento`,`cpf`,`rg`,`email`,`telefone`,`celular`,`id_funcao_fk`,`salario`,`cep`,`endereco`,`bairro`,`cidade`,`estado`,`id_pais_fk`,`excluido`) VALUES 
 (11,'Marcos César da Silva Mello','16/02/1996','441.729.778-98','444645482','marcos_smello@hotmail.com','(19) 3684-1615','(19) 992190452',3,2000,'13720000','Rua João Paulino de Carvalho 208','João de Souza','São José do Rio Pardo','SP',26,0),
 (12,'teste','  /  /    ','   .   .   -  ','','','(  )     -    ','(  )          ',1,0,'        ','','','','  ',26,0),
 (13,'teste 1','  /  /    ','   .   .   -  ','','','(  )     -    ','(  )          ',1,0,'        ','','','','  ',26,0);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;


--
-- Definition of table `funcionario_funcao`
--

DROP TABLE IF EXISTS `funcionario_funcao`;
CREATE TABLE `funcionario_funcao` (
  `id_funcao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_funcao`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `funcionario_funcao`
--

/*!40000 ALTER TABLE `funcionario_funcao` DISABLE KEYS */;
INSERT INTO `funcionario_funcao` (`id_funcao`,`nome`,`excluido`) VALUES 
 (1,'Sem função',0),
 (2,'Auxiliar',0),
 (3,'Programador',0),
 (4,'No!!!!',1),
 (5,'Engenheiro',0),
 (6,'Help desk',0),
 (7,'Suporte técnico',0);
/*!40000 ALTER TABLE `funcionario_funcao` ENABLE KEYS */;


--
-- Definition of table `ordem_servico`
--

DROP TABLE IF EXISTS `ordem_servico`;
CREATE TABLE `ordem_servico` (
  `id_ordem_servico` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` text NOT NULL,
  `id_cliente_fk` int(10) unsigned NOT NULL,
  `id_funcionario_fk` int(10) unsigned NOT NULL,
  `solicitante` varchar(50) NOT NULL,
  `flag_materiais_instalados` tinyint(1) NOT NULL,
  `flag_servico_conferido` tinyint(1) NOT NULL,
  `flag_local_limpo` tinyint(1) NOT NULL,
  `flag_cliente_satsfeito` tinyint(1) NOT NULL,
  `nota_atendimento` tinyint(3) unsigned NOT NULL,
  `flag_atendeu_necessidade` tinyint(1) NOT NULL,
  `finalizado` tinyint(1) NOT NULL,
  `valor_mao_de_obra` float NOT NULL,
  `valor_produtos` float NOT NULL,
  `acrescimo` float NOT NULL,
  `desconto` float NOT NULL,
  `valor_total` float NOT NULL,
  `excluido` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_ordem_servico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ordem_servico`
--

/*!40000 ALTER TABLE `ordem_servico` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordem_servico` ENABLE KEYS */;


--
-- Definition of table `ordem_servico_funcionario`
--

DROP TABLE IF EXISTS `ordem_servico_funcionario`;
CREATE TABLE `ordem_servico_funcionario` (
  `id_funcionario` int(10) unsigned NOT NULL,
  `id_ordem_servico` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ordem_servico_funcionario`
--

/*!40000 ALTER TABLE `ordem_servico_funcionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordem_servico_funcionario` ENABLE KEYS */;


--
-- Definition of table `ordem_servico_produto`
--

DROP TABLE IF EXISTS `ordem_servico_produto`;
CREATE TABLE `ordem_servico_produto` (
  `id_produto` int(10) unsigned NOT NULL,
  `id_centro_estoque` int(10) unsigned NOT NULL,
  `quantidade` int(10) unsigned NOT NULL,
  `valor_unitario` float NOT NULL,
  `valor_total` float NOT NULL,
  `id_ordem_servico` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ordem_servico_produto`
--

/*!40000 ALTER TABLE `ordem_servico_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordem_servico_produto` ENABLE KEYS */;


--
-- Definition of table `pais`
--

DROP TABLE IF EXISTS `pais`;
CREATE TABLE `pais` (
  `id_pais` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `excluido` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pais`
--

/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` (`id_pais`,`nome`,`excluido`) VALUES 
 (1,'Canadá',0),
 (2,'Estados Unidos',0),
 (3,'Antígua e Barbuda',0),
 (4,'Bahamas',0),
 (5,'Barbados',0),
 (6,'Cuba',0),
 (7,'Dominica',0),
 (8,'Granada',0),
 (9,'Haiti',0),
 (10,'Jamaica',0),
 (11,'República Dominicana',0),
 (12,'São Cristóvão e Neves',0),
 (13,'Santa Lúcia',0),
 (14,'São Vicente e Granadinas',0),
 (15,'Trinidad e Tobago',0),
 (16,'Belize',0),
 (17,'Costa Rica',0),
 (18,'El Salvador',0),
 (19,'Guatemala',0),
 (20,'Honduras',0),
 (21,'México',0),
 (22,'Nicarágua',0),
 (23,'Panamá',0),
 (24,'Argentina',0),
 (25,'Bolívia',0),
 (26,'Brasil',0),
 (27,'Chile',0),
 (28,'Colômbia',0),
 (29,'Equador',0),
 (30,'Guiana',0),
 (31,'Paraguai',0),
 (32,'Peru',0),
 (33,'Suriname',0),
 (34,'Uruguai',0),
 (35,'Venezuela',0),
 (36,'Cazaquistão',0),
 (37,'Quirguistão',0),
 (38,'Tadjiquistão',0),
 (39,'Turquemenistão',0),
 (40,'Uzbequistão',0),
 (41,'China',0),
 (42,'Coreia do Norte',0),
 (43,'Japão',0),
 (44,'Mongólia',0),
 (45,'Coreia do Sul',0),
 (46,'Afeganistão',0),
 (47,'Bangladesh',0),
 (48,'Butão',0),
 (49,'Índia',0),
 (50,'Irã',0),
 (51,'Maldivas',0),
 (52,'Nepal',0),
 (53,'Paquistão',0),
 (54,'Sri Lanka',0),
 (55,'Brunei Darussalam',0),
 (56,'Camboja',0),
 (57,'Filipinas',0),
 (58,'Indonésia',0),
 (59,'Laos',0),
 (60,'Malásia',0),
 (61,'Myanmar',0),
 (62,'Singapura',0),
 (63,'Tailândia',0),
 (64,'Timor-Leste',0),
 (65,'Vietname',0),
 (66,'Arménia',0),
 (67,'Azerbaijão',0),
 (68,'Bahrein',0),
 (69,'Chipre',0),
 (70,'Geórgia',0),
 (71,'Iraque',0),
 (72,'Israel',0),
 (73,'Jordânia',0),
 (74,'Kuwait',0),
 (75,'Líbano',0),
 (76,'Omã',0),
 (77,'Qatar',0),
 (78,'Arábia Saudita',0),
 (79,'Síria',0),
 (80,'Turquia',0),
 (81,'Emirados Árabes Unidos',0),
 (82,'Iêmen',0);
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;


--
-- Definition of table `produto`
--

DROP TABLE IF EXISTS `produto`;
CREATE TABLE `produto` (
  `id_produto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(70) NOT NULL,
  `id_setor_fk` tinyint(3) unsigned NOT NULL,
  `valor_compra` float NOT NULL DEFAULT '0',
  `valor_venda` float NOT NULL DEFAULT '0',
  `valor_comissao` float DEFAULT '0',
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produto`
--

/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` (`id_produto`,`nome`,`id_setor_fk`,`valor_compra`,`valor_venda`,`valor_comissao`,`excluido`) VALUES 
 (1,'Coca-Cola',1,6,0,0,1),
 (2,'fanta',2,5,5,0,1),
 (3,'teste',4,40,0,0,1),
 (4,'mmmmm',0,43,0,0,0),
 (5,'teste 2',8,40,68,0,1),
 (6,'wewew',1,30,60,0,1),
 (7,'Arroz',10,6,8.4,1,1),
 (8,'Câmera tipo A',14,58,92.8,10,0),
 (9,'Câmera tipo B',14,75,108.75,21,0),
 (10,'Haste',16,4.5,5,0,0);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;


--
-- Definition of table `produto_setor`
--

DROP TABLE IF EXISTS `produto_setor`;
CREATE TABLE `produto_setor` (
  `id_setor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_setor`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produto_setor`
--

/*!40000 ALTER TABLE `produto_setor` DISABLE KEYS */;
INSERT INTO `produto_setor` (`id_setor`,`nome`,`excluido`) VALUES 
 (1,'Sem setor',0),
 (2,'teste',1),
 (3,'teste 2',1),
 (4,'teste 3',1),
 (5,'teste  4',1),
 (7,'aaaaabbbb',1),
 (8,'abc',1),
 (9,'aaasdsdsdsd',1),
 (10,'Cereais',1),
 (11,'Refrigerante',1),
 (12,'Sucos',1),
 (13,'Vegetais',1),
 (14,'Câmeras',0),
 (15,'Alarmes',0),
 (16,'Cerca elétrica',0),
 (17,'Outros',0);
/*!40000 ALTER TABLE `produto_setor` ENABLE KEYS */;


--
-- Definition of table `servico`
--

DROP TABLE IF EXISTS `servico`;
CREATE TABLE `servico` (
  `id_servico` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(70) NOT NULL,
  `id_setor_fk` int(10) unsigned NOT NULL,
  `valor_venda` float NOT NULL,
  `valor_comissao` float NOT NULL,
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `servico`
--

/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` (`id_servico`,`nome`,`id_setor_fk`,`valor_venda`,`valor_comissao`,`excluido`) VALUES 
 (1,'Teste',2,300,5,0),
 (2,'Teste 1',1,200,10,1);
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;


--
-- Definition of table `servico_setor`
--

DROP TABLE IF EXISTS `servico_setor`;
CREATE TABLE `servico_setor` (
  `id_setor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_setor`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `servico_setor`
--

/*!40000 ALTER TABLE `servico_setor` DISABLE KEYS */;
INSERT INTO `servico_setor` (`id_setor`,`nome`,`excluido`) VALUES 
 (1,'Sem setor',0),
 (2,'Pintura',0),
 (3,'Marcenaria',0),
 (4,'Teste',1);
/*!40000 ALTER TABLE `servico_setor` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id_usuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(15) NOT NULL,
  `senha` varchar(15) NOT NULL,
  `tipo` varchar(15) NOT NULL,
  `excluido` tinyint(3) unsigned DEFAULT '0',
  PRIMARY KEY (`id_usuario`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id_usuario`,`nome`,`senha`,`tipo`,`excluido`) VALUES 
 (1,'Administrador','123','Administrador',0),
 (2,'marcos','123','Administrador',0),
 (3,'lucas1','1234','Normal',1),
 (4,'lucas','123','Normal',0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
