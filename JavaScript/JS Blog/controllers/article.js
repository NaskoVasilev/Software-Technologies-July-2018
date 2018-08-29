const Article = require('../models').Article;
const User = require('../models').User;
const Comment=require('../models').Comment;

module.exports = {
    createGet: (req, res) => {
        res.render('article/create');
    },
    createPost: (req, res) => {
        let articleArgs = req.body;
        let errorMessage = '';

        if (!req.isAuthenticated()) {
            errorMessage = 'You should be logged in to make articles';
        } else if (!articleArgs.title) {
            errorMessage = 'Invalid title!';
        }
        else if (!articleArgs.content) {
            errorMessage = 'Invalid content';
        }

        if (errorMessage) {
            res.render('article/create', {error: errorMessage});
            return;
        }

        articleArgs.authorId = req.user.id;
        Article.create(articleArgs).then(article => {
            res.redirect('/');
        }).catch(err => {
            console.log(err.message);
            res.render('/article/create', {error: err.message})
        })
    },
    details: (req, res) => {
        let articleId = req.params.id;

        Article.findById(articleId, {
            include: [
                {
                    model: User
                },
                {
                    model:Comment,
                    include: [User]
                }
            ]
        }).then(article => {
            res.render('article/details', {article});
        });
    },
    editGet: (req, res) => {
        let articleId = req.params.id;

        Article.findById(articleId)
            .then(article => {
                res.render('article/edit', article.dataValues);
            });
    },
    editPost: (req, res) => {
        let articleArgs = req.body;
        let articleId = req.params.id;

        Article.findById(articleId)
            .then(article => {
                article.update(articleArgs)
                    .then(() => {
                        res.redirect('/');
                    })
            })
    },
    deleteGet: (req, res) => {
        let articleId = req.params.id;

        Article.findById(articleId)
            .then(article => {
                res.render('article/delete', article.dataValues);
            })
    },
    deletePost: (req, res) => {
        let articleId = req.params.id;

        Article.findById(articleId)
            .then(article => {
                article.destroy()
                    .then(() => {
                        res.redirect('/');
                    })
            })
    },
    commentAddPost:(req,res)=>{
        let body=req.body;
        let author=req.user;
        let comment={
            content:body.comment,
            authorId:author.id,
            articleId:req.params.id,
        };

        Comment.create(comment)
            .then(()=>
            res.redirect(`/article/details/${req.params.id}`));

    },

}
