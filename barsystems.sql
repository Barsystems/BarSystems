-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 18-Dez-2014 às 22:00
-- Versão do servidor: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `barsystems`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `centros_estoque`
--

CREATE TABLE IF NOT EXISTS `centros_estoque` (
  `codigo_centro_estoque` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL DEFAULT '',
  `excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `Data_Cadastro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo_centro_estoque`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `compras`
--

CREATE TABLE IF NOT EXISTS `compras` (
  `Codigo_Compra` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(45) NOT NULL,
  `Codigo_Fornecedor` int(10) unsigned NOT NULL,
  `Numero_Nota` varchar(80) DEFAULT NULL,
  `Data` datetime NOT NULL,
  `Responsavel` varchar(45) DEFAULT NULL,
  `Valor` float NOT NULL,
  `Codigo_Forma_Pagamento` varchar(35) NOT NULL,
  `Parcelas` int(11) NOT NULL,
  `Excluido` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `Data_Cadastro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Codigo_Compra`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `despesas`
--

CREATE TABLE IF NOT EXISTS `despesas` (
  `Codigo_Despesa` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Codigo_Compra` int(11) DEFAULT NULL,
  `Descricao` text NOT NULL,
  `Responsavel` varchar(50) DEFAULT NULL,
  `Codigo_Fornecedor` int(10) unsigned NOT NULL,
  `Parcelas` smallint(5) unsigned NOT NULL,
  `Numero_Parcela` smallint(5) unsigned NOT NULL,
  `Data_Pagamento` datetime DEFAULT NULL,
  `Data_Vencimento` datetime NOT NULL,
  `Forma_Pagamento` varchar(35) NOT NULL,
  `Valor` float NOT NULL,
  `Valor_Juros` float NOT NULL,
  `Valor_Total` float NOT NULL,
  `Liquidado` tinyint(1) NOT NULL,
  `Codigo_Categoria` int(10) unsigned DEFAULT NULL,
  `Codigo_Usuario` int(10) unsigned NOT NULL,
  `Excluido` tinyint(3) unsigned NOT NULL,
  `Data_Cadastro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Codigo_Despesa`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedores`
--

CREATE TABLE IF NOT EXISTS `fornecedores` (
  `Codigo_Fornecedor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Razao_Social` varchar(45) NOT NULL,
  `Nome_Fantasia` varchar(45) NOT NULL,
  `CNPJ` varchar(20) NOT NULL,
  `CEP` varchar(10) NOT NULL,
  `Cidade` varchar(45) NOT NULL,
  `Estado` varchar(2) NOT NULL,
  `Endereco` varchar(45) NOT NULL,
  `Bairro` varchar(45) NOT NULL,
  `Telefone` varchar(15) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Inscricao_Estadual` varchar(45) NOT NULL,
  `Observacoes` text NOT NULL,
  `Foto` text,
  `Excluido` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`Codigo_Fornecedor`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE IF NOT EXISTS `produtos` (
  `id_produto` int(10) unsigned NOT NULL DEFAULT '0',
  `descricao` varchar(70) NOT NULL DEFAULT '',
  `quantidade_por_caixa` int(10) unsigned NOT NULL DEFAULT '0',
  `tipo` varchar(25) NOT NULL DEFAULT '',
  `excluido` tinyint(1) NOT NULL DEFAULT '0',
  `data_cadastro` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`id_produto`, `descricao`, `quantidade_por_caixa`, `tipo`, `excluido`, `data_cadastro`) VALUES
(1, 'cerveja', 12, 'cervejao', 0, '2014-12-17 00:00:00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos_centro_estoque`
--

CREATE TABLE IF NOT EXISTS `produtos_centro_estoque` (
  `Codigo_Produto` int(10) unsigned NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Codigo_Centro_Estoque` tinyint(3) unsigned NOT NULL,
  `Quantidade` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos_compra`
--

CREATE TABLE IF NOT EXISTS `produtos_compra` (
  `id_produtos_compra` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_produtos` int(10) unsigned NOT NULL DEFAULT '0',
  `valor_compra` float NOT NULL DEFAULT '0',
  `valor_unidade` float NOT NULL DEFAULT '0',
  `valor_venda_unidade` float NOT NULL DEFAULT '0',
  `data_compra` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `quantidade` int(10) unsigned NOT NULL DEFAULT '0',
  `data_cadastro` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `id_centro_estoque` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_produtos_compra`),
  KEY `id_produtos` (`id_produtos`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 PACK_KEYS=1 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `produtos_compra`
--

INSERT INTO `produtos_compra` (`id_produtos_compra`, `id_produtos`, `valor_compra`, `valor_unidade`, `valor_venda_unidade`, `data_compra`, `quantidade`, `data_cadastro`, `id_centro_estoque`) VALUES
(1, 1, 3, 4, 5, '2014-12-17 00:00:00', 20, '2014-12-17 02:00:00', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `Codigo_Usuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Nome` varchar(15) NOT NULL,
  `Senha` varchar(15) NOT NULL,
  `Tipo` varchar(15) NOT NULL,
  `Foto` text,
  `Excluido` tinyint(3) unsigned DEFAULT '0',
  `Data_Cadastro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Codigo_Usuario`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `produtos_compra`
--
ALTER TABLE `produtos_compra`
  ADD CONSTRAINT `id_produtos` FOREIGN KEY (`id_produtos`) REFERENCES `produtos` (`id_produto`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
