-- Copiando estrutura para tabela erudio.person
CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(80) NOT NULL,
  `last_name` varchar(80) NOT NULL,
  `address` varchar(255) NOT NULL,
  `gender` varchar(24) NOT NULL,
  PRIMARY KEY (`id`)
) ;

