mocha.setup('bdd');
mocha.checkLeaks();

require('kevalidate_test');

mocha.run();
