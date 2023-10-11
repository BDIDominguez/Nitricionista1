-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-10-2023 a las 02:37:28
-- Versión del servidor: 8.0.30
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `nutricionista`
--
CREATE DATABASE IF NOT EXISTS `nutricionista` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `nutricionista`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comidas`
--

DROP TABLE IF EXISTS `comidas`;
CREATE TABLE `comidas` (
  `idcomida` int NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `receta` varchar(120) DEFAULT NULL,
  `calorias` int DEFAULT NULL,
  `estado` tinyint DEFAULT NULL,
  `peso` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controles`
--

DROP TABLE IF EXISTS `controles`;
CREATE TABLE `controles` (
  `idControl` int NOT NULL,
  `idPaciente` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `altura` double DEFAULT NULL,
  `cintura` double DEFAULT NULL,
  `gasenergetico` double DEFAULT NULL,
  `IMC` double DEFAULT NULL,
  `proximacita` date DEFAULT NULL,
  `estado` tinyint DEFAULT NULL,
  `obs` text COLLATE utf8mb4_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `controles`
--

INSERT INTO `controles` (`idControl`, `idPaciente`, `fecha`, `peso`, `altura`, `cintura`, `gasenergetico`, `IMC`, `proximacita`, `estado`, `obs`) VALUES
(3, 1, '2023-10-08', 50, 50, 50, 50, 50, '2023-10-15', 1, 'Lechonnnnn!!!'),
(4, 1, '2023-10-15', 50, 50, 50, 50, 50, '2023-10-22', 0, 'Segunda cita del Lechos!!!'),
(6, 4, '2023-10-09', 73.5, 1.69, 45.5, 45, 25.73, '2023-10-17', 1, 'La paciende precenta sintomas de transtornos pero siquiatricos!!!!'),
(7, 4, '2023-10-17', 75.5, 1.69, 48, 60, 26.43, '2023-10-24', 1, 'Sigue con sus claros problemas mentales, no entiende que no necesita\nun nutricionista si no un psicologo XD'),
(8, 4, '2023-10-10', 74.5, 1.69, 46, 35, 26.08, '2023-10-11', 1, 'Sigue con sus claros problemas mentales, no entiende que no necesita\nun nutricionista si no un psicologo XD');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dietacomidas`
--

DROP TABLE IF EXISTS `dietacomidas`;
CREATE TABLE `dietacomidas` (
  `iddietacomida` int NOT NULL,
  `idcomida` int DEFAULT NULL,
  `iddieta` int DEFAULT NULL,
  `porcion` double DEFAULT NULL,
  `horario` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dietas`
--

DROP TABLE IF EXISTS `dietas`;
CREATE TABLE `dietas` (
  `iddieta` int NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `idpaciente` int DEFAULT NULL,
  `fecinicio` date DEFAULT NULL,
  `fecfinal` date DEFAULT NULL,
  `pesoinicial` double DEFAULT NULL,
  `pesofinal` double DEFAULT NULL,
  `estado` tinyint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
CREATE TABLE `pacientes` (
  `idpaciente` int NOT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `dni` int NOT NULL,
  `domicilio` varchar(60) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `estado` tinyint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`idpaciente`, `nombre`, `dni`, `domicilio`, `telefono`, `estado`) VALUES
(1, 'Dominguez, Benardo', 30541575, 'La Providencia N 62', '+543885273263', 1),
(2, 'Dominguez, Dario', 30541576, 'La providencia nro 62', '+543885273263', 1),
(3, 'Dominguez, Ismael', 30541577, 'La Providencia Nro 62', '+543885273263', 1),
(4, 'Vasualdo, Ines', 30150502, 'La providencia nro 62', '+543885273827', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comidas`
--
ALTER TABLE `comidas`
  ADD PRIMARY KEY (`idcomida`);

--
-- Indices de la tabla `controles`
--
ALTER TABLE `controles`
  ADD PRIMARY KEY (`idControl`),
  ADD KEY `idPaciente` (`idPaciente`);

--
-- Indices de la tabla `dietacomidas`
--
ALTER TABLE `dietacomidas`
  ADD PRIMARY KEY (`iddietacomida`),
  ADD KEY `dietacomidas_ibfk_1` (`iddieta`),
  ADD KEY `dietacomidas_ibfk_2` (`idcomida`);

--
-- Indices de la tabla `dietas`
--
ALTER TABLE `dietas`
  ADD PRIMARY KEY (`iddieta`),
  ADD KEY `idpaciente` (`idpaciente`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`idpaciente`),
  ADD UNIQUE KEY `dni_UNIQUE` (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comidas`
--
ALTER TABLE `comidas`
  MODIFY `idcomida` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `controles`
--
ALTER TABLE `controles`
  MODIFY `idControl` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `dietacomidas`
--
ALTER TABLE `dietacomidas`
  MODIFY `iddietacomida` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dietas`
--
ALTER TABLE `dietas`
  MODIFY `iddieta` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `idpaciente` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `controles`
--
ALTER TABLE `controles`
  ADD CONSTRAINT `controles_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`idpaciente`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `dietacomidas`
--
ALTER TABLE `dietacomidas`
  ADD CONSTRAINT `dietacomidas_ibfk_1` FOREIGN KEY (`iddieta`) REFERENCES `dietas` (`iddieta`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `dietacomidas_ibfk_2` FOREIGN KEY (`idcomida`) REFERENCES `comidas` (`idcomida`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `dietas`
--
ALTER TABLE `dietas`
  ADD CONSTRAINT `dietas_ibfk_1` FOREIGN KEY (`idpaciente`) REFERENCES `pacientes` (`idpaciente`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
